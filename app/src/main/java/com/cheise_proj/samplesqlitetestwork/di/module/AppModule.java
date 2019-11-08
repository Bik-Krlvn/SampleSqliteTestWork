package com.cheise_proj.samplesqlitetestwork.di.module;

import android.app.Application;
import android.content.Context;

import com.cheise_proj.samplesqlitetestwork.di.module.data.DataModule;
import com.cheise_proj.samplesqlitetestwork.di.module.domain.DomainModule;
import com.cheise_proj.samplesqlitetestwork.di.module.local.LocalModule;
import com.cheise_proj.samplesqlitetestwork.di.module.presentation.PresentationModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {PresentationModule.class, DomainModule.class, DataModule.class, LocalModule.class})
public class AppModule {
    @Provides
    public Context provideContext(Application application){
        return application.getBaseContext();
    }
}
