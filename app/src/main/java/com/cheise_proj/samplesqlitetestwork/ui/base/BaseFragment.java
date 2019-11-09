package com.cheise_proj.samplesqlitetestwork.ui.base;

import com.cheise_proj.presentation.factory.ViewModelFactory;
import com.cheise_proj.samplesqlitetestwork.utils.PreferenceUtil;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {
    @Inject
    protected ViewModelFactory viewModelFactory;

    @Inject
    protected PreferenceUtil preferenceUtil;
}
