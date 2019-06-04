package com.amar.moviesearch.ui;

import androidx.test.rule.ActivityTestRule;

import com.amar.moviesearch.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class MovieSearchActivityTest {
    @Rule
    public ActivityTestRule<MovieSearchActivity> activityRule
            = new ActivityTestRule<>(
            MovieSearchActivity.class, true, false);

    @Test
    public void defaultViewTest() {
        activityRule.launchActivity(null);

        onView(withId(R.id.empty_view))
                .check(matches(withText(R.string.search_movies)));
        onView(withId(R.id.search))
                .check(matches(isDisplayed()));
    }
}