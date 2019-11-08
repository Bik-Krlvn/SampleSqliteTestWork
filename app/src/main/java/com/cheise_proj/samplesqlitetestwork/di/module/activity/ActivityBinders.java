package com.cheise_proj.samplesqlitetestwork.di.module.activity;

import com.cheise_proj.samplesqlitetestwork.di.module.activity.fragment.AuthFragmentBinders;
import com.cheise_proj.samplesqlitetestwork.ui.auth.AuthActivity;
import com.cheise_proj.samplesqlitetestwork.ui.profile.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module()
public abstract class ActivityBinders {
    @ContributesAndroidInjector
    public abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = AuthFragmentBinders.class)
    public abstract AuthActivity contributeAuthActivity();
}
