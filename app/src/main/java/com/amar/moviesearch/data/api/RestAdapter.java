package com.amar.moviesearch.data.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapter {

    private Retrofit.Builder retrofit;

    public RestAdapter(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Retrofit.Builder getRetrofitInstance() {
        return retrofit;
    }
}
