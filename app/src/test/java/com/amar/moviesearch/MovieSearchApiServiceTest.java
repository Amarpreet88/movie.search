package com.amar.moviesearch;

import com.amar.moviesearch.data.api.MovieSearchApi;
import com.amar.moviesearch.data.model.MovieSearch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

public class MovieSearchApiServiceTest extends ApiAbstract<MovieSearchApi> {

    private MovieSearchApi service;

    @Before
    public void initService() {
        this.service = createService(MovieSearchApi.class);
    }

    @Test
    public void fetchMovieTest() throws IOException {
        enqueueResponse("test_movie.json");
        Response<MovieSearch> movieSearch1 = service.getMovieList("4355e65d", "hello").execute();
        Assert.assertEquals("Hello, My Name Is Doris", movieSearch1.body().getSearch().get(0).getTitle());
        Assert.assertEquals("2015", movieSearch1.body().getSearch().get(0).getYear());
        Assert.assertEquals("https://m.media-amazon.com/images/M/MV5BMTg0NTM3MTI1MF5BMl5BanBnXkFtZTgwMTAzNTAzNzE@._V1_SX300.jpg",
                movieSearch1.body().getSearch().get(0).getPoster());
    }
}
