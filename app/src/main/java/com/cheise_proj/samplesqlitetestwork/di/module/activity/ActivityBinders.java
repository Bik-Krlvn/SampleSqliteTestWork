package com.cheise_proj.samplesqlitetestwork.di.module.activity;

import com.cheise_proj.samplesqlitetestwork.di.module.activity.fragment.AuthFragmentBinders;
import com.cheise_proj.samplesqlitetestwork.di.module.activity.fragment.ContactFragmentBinders;
import com.cheise_proj.samplesqlitetestwork.ui.auth.AuthActivity;
import com.cheise_proj.samplesqlitetestwork.ui.main.NavigationActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module()
public abstract class ActivityBinders {

    @ContributesAndroidInjector(modules = AuthFragmentBinders.class)
    public abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(modules = ContactFragmentBinders.class)
    public abstract NavigationActivity contributeNavigationActivity();
}
