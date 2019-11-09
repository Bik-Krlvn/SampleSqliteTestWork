package com.cheise_proj.samplesqlitetestwork.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cheise_proj.presentation.model.Contact;
import com.cheise_proj.samplesqlitetestwork.di.module.data.DataModule;
import com.cheise_proj.samplesqlitetestwork.di.module.domain.DomainModule;
import com.cheise_proj.samplesqlitetestwork.di.module.local.LocalModule;
import com.cheise_proj.samplesqlitetestwork.di.module.presentation.PresentationModule;
import com.cheise_proj.samplesqlitetestwork.utils.PreferenceUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {PresentationModule.class, DomainModule.class, DataModule.class, LocalModule.class})
public class AppModule {
    @Provides
    public Context provideContext(Application application) {
        return application.getBaseContext();
    }

    @Singleton
    @Provides
    public SharedPreferences sharedPreferences(Application application) {
        return application.getApplicationContext().getSharedPreferences("localPref", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public PreferenceUtil providePreferenceUtil(SharedPreferences sharedPreferences) {
        return new PreferenceUtil(sharedPreferences);
    }
}
