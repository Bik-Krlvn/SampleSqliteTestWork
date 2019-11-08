package com.cheise_proj.samplesqlitetestwork.di.module.domain;

import com.cheise_proj.domain.qualifiers.Background;
import com.cheise_proj.domain.qualifiers.Foreground;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module(includes = DomainModule.Binders.class)
public class DomainModule {
    @Module
    interface Binders {

    }

    @Background
    @Provides
    Scheduler provideBackgroundScheduler() {
        return Schedulers.io();
    }

    @Foreground
    @Provides
    Scheduler provideForegroundScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
