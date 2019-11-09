package com.cheise_proj.samplesqlitetestwork.di.module.activity.fragment;

import com.cheise_proj.samplesqlitetestwork.ui.main.ContactListFragment;
import com.cheise_proj.samplesqlitetestwork.ui.main.CreateContactFragment;
import com.cheise_proj.samplesqlitetestwork.ui.main.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ContactFragmentBinders {
    @ContributesAndroidInjector
    abstract ContactListFragment contactListFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment profileFragment();

    @ContributesAndroidInjector
    abstract CreateContactFragment createContactFragment();
}
