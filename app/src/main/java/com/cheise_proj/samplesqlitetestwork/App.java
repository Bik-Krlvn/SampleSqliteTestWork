package com.cheise_proj.samplesqlitetestwork;

import android.app.Application;

import com.cheise_proj.samplesqlitetestwork.di.AppComponent;
import com.cheise_proj.samplesqlitetestwork.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {
    AppComponent appComponent =  DaggerAppComponent.builder().application(this).build();

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return appComponent;
    }
}
