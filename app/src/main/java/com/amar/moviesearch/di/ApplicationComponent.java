package com.amar.moviesearch.di;

import com.amar.moviesearch.factory.MovieSearchFactory;
import com.amar.moviesearch.ui.MovieSearchActivity;
import com.amar.moviesearch.MovieSearchApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MovieSearchApplication movieSearchApplication);

    void inject(MovieSearchActivity movieSearchActivity);

    void inject(MovieSearchFactory viewModelFactory);
}