package com.cheise_proj.samplesqlitetestwork.di.module.activity.fragment;

import com.cheise_proj.samplesqlitetestwork.ui.auth.fragment.LoginFragment;
import com.cheise_proj.samplesqlitetestwork.ui.auth.fragment.RegisterFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthFragmentBinders {

    @ContributesAndroidInjector
    public abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector
    public abstract RegisterFragment contributeRegisterFragment();
}
