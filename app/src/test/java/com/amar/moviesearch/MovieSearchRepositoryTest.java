package com.amar.moviesearch;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.amar.moviesearch.data.api.MovieSearchApi;
import com.amar.moviesearch.data.model.MovieSearch;
import com.amar.moviesearch.data.model.MovieSearchDetails;
import com.amar.moviesearch.data.repository.MovieSearchRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class MovieSearchRepositoryTest {

    @Mock
    private MovieSearchApi api;

    @Mock
    private Call<MovieSearch> mockedMovieSearch;

    @Mock
    private Observer<MovieSearch> movieSearchObserver;


    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private MovieSearchRepository movieSearchRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        movieSearchRepository = new MovieSearchRepository(api);
    }

    @Test
    public void getMovies_NetworkNotCalled() {
        LiveData<MovieSearch> data = movieSearchRepository.getMovieList();
        movieSearchRepository.getMovieList().observeForever(movieSearchObserver);

        assertEquals(null, data.getValue());
    }

    @Test
    public void getMovies_NetworkCalledSuccess() {
        LiveData<MovieSearch> movieSearchedList = movieSearchRepository.getMovieList();
        movieSearchRepository.getMovieList().observeForever(movieSearchObserver);

        List<MovieSearchDetails> list = new ArrayList<>();
        list.add(new MovieSearchDetails());
        list.add(new MovieSearchDetails());
        list.add(new MovieSearchDetails());
        MovieSearch search = new MovieSearch();
        search.setSearch(list);

        when(api.getMovieList("4355e65d", "hello"))
                .thenReturn(mockedMovieSearch);

        doAnswer(invocation -> {
            Callback<MovieSearch> callback = invocation.getArgument(0);
            MovieSearch response = new MovieSearch();
            response.setSearch(list);
            callback.onResponse(mockedMovieSearch, Response.success(response));
            return null;
        }).when(mockedMovieSearch).enqueue(any(Callback.class));

        movieSearchRepository.retrieveMovieList("hello");
        assertEquals(3, movieSearchedList.getValue().getSearch().size());
    }
}
