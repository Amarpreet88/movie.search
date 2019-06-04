package com.amar.moviesearch.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.amar.moviesearch.data.model.MovieSearch;
import com.amar.moviesearch.data.repository.MovieSearchRepository;

public class MovieSearchViewModel extends ViewModel {

    private MovieSearchRepository movieSearchRepository;

    public MovieSearchViewModel(MovieSearchRepository movieSearchRepository) {
        this.movieSearchRepository = movieSearchRepository;
    }

    public void getMovieList(String title) {
        movieSearchRepository.retrieveMovieList(title);
    }

    public LiveData<MovieSearch> getMovieList() {
        return movieSearchRepository.getMovieList();
    }
}
