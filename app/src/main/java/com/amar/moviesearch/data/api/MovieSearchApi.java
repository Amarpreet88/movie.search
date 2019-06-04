package com.amar.moviesearch.data.api;

import com.amar.moviesearch.data.model.MovieSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieSearchApi {

    @GET("http://www.omdbapi.com/?")
    Call<MovieSearch> getMovieList(@Query("apikey") String apiKey,
                                   @Query("s") String search);

}
