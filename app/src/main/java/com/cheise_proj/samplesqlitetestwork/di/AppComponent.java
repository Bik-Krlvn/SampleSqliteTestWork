package com.cheise_proj.samplesqlitetestwork.di;

import android.app.Application;

import com.cheise_proj.samplesqlitetestwork.App;
import com.cheise_proj.samplesqlitetestwork.di.module.AppModule;
import com.cheise_proj.samplesqlitetestwork.di.module.activity.ActivityBinders;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules =
        {
                AndroidSupportInjectionModule.class,
                AppModule.class, ActivityBinders.class
        })
@Singleton
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    @Override
    void inject(App instance);
}
