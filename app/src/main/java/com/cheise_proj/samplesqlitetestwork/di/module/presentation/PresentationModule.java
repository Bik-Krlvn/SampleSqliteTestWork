package com.cheise_proj.samplesqlitetestwork.di.module.presentation;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cheise_proj.presentation.factory.ViewModelFactory;
import com.cheise_proj.presentation.viewmodel.UserViewModel;
import com.cheise_proj.samplesqlitetestwork.di.key.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(includes = {PresentationModule.Binders.class})
public class PresentationModule {
    @Module
    interface Binders {
        @Binds
        ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

        @Binds
        @ViewModelKey(UserViewModel.class)
        @IntoMap
        ViewModel bindUserViewModel(UserViewModel userViewModel);
    }
}
