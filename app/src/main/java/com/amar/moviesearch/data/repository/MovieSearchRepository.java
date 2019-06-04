package com.amar.moviesearch.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.amar.moviesearch.data.api.MovieSearchApi;
import com.amar.moviesearch.data.model.MovieSearch;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearchRepository {

    private final String API_KEY = "4355e65d";

    private MovieSearchApi movieSearchApi;
    private final MutableLiveData<MovieSearch> movieSearchedList = new MutableLiveData<>();

    @Inject
    public MovieSearchRepository(MovieSearchApi api) {
        movieSearchApi = api;
        movieSearchedList.postValue(null);
    }

    public void retrieveMovies(String title) {
        movieSearchApi.getMovieList(API_KEY, title)
                .enqueue(new Callback<MovieSearch>() {
                    @Override
                    public void onResponse(Call<MovieSearch> call, Response<MovieSearch> response) {
                        MovieSearch movieSearch = response.body();
                        movieSearchedList.postValue(movieSearch);
                    }

                    @Override
                    public void onFailure(Call<MovieSearch> call, Throwable t) {
                        movieSearchedList.postValue(null);
                    }
                });
    }

    public LiveData<MovieSearch> getMovieList() {
        return movieSearchedList;
    }
}
