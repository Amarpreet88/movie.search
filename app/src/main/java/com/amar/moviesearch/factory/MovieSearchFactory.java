package com.amar.moviesearch.factory;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.amar.moviesearch.data.repository.MovieSearchRepository;
import com.amar.moviesearch.ui.MovieSearchViewModel;

import javax.inject.Inject;

public class MovieSearchFactory implements ViewModelProvider.Factory {

    private final MovieSearchRepository movieSearchRepository;

    @Inject
    public MovieSearchFactory(MovieSearchRepository repository) {
        movieSearchRepository = repository;
    }

    @NonNull
    @Override
    public MovieSearchViewModel create(@NonNull Class modelClass) {
        return new MovieSearchViewModel(movieSearchRepository);
    }
}