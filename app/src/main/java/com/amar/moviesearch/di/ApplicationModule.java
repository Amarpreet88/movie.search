package com.amar.moviesearch.di;

import android.app.Application;
import android.content.Context;

import com.amar.moviesearch.MovieSearchApplication;
import com.amar.moviesearch.R;
import com.amar.moviesearch.data.api.MovieSearchApi;
import com.amar.moviesearch.data.api.RestAdapter;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(MovieSearchApplication app) {
        mApplication = app;
    }

    @Provides
    Context providesContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    public MovieSearchApplication providesApplication() {
        return (MovieSearchApplication) mApplication;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        return httpClient.build();
    }

    @Provides
    public RestAdapter providesRestAdapter(OkHttpClient okHttpClient) {
        return new RestAdapter(okHttpClient);
    }

    @Provides
    @Singleton
    public MovieSearchApi providesMovieSearchApi(Context context, RestAdapter adapter) {
        Retrofit retrofit = adapter.getRetrofitInstance()
                .baseUrl(context.getString(R.string.base_url))
                .build();
        return retrofit.create(MovieSearchApi.class);

    }


}