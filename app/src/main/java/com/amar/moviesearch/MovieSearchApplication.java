package com.amar.moviesearch;

import android.app.Application;

import com.amar.moviesearch.di.ApplicationComponent;
import com.amar.moviesearch.di.ApplicationModule;
import com.amar.moviesearch.di.DaggerApplicationComponent;

public class MovieSearchApplication extends Application {

    private static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    public void initDagger() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
            mApplicationComponent.inject(this);
        }
    }

    public static ApplicationComponent getDaggerComponent() {
        return mApplicationComponent;
    }

}
