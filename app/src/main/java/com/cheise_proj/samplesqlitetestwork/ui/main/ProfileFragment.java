package com.cheise_proj.samplesqlitetestwork.ui.main;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.cheise_proj.presentation.model.User;
import com.cheise_proj.presentation.viewmodel.UserViewModel;
import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseFragment;

import java.io.File;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class ProfileFragment extends BaseFragment {
    private TextView mUserId;
    private TextView mUsername;
    private TextView mEmail;
    private ImageView mImageProfile;
    private UserViewModel userViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUserId = view.findViewById(R.id.tv_user_id);
        mUsername = view.findViewById(R.id.tv_username);
        mEmail = view.findViewById(R.id.tv_email);
        mImageProfile = view.findViewById(R.id.img_profile);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configViewModel();
    }

    /**
     * Initialize view model
     */
    private void configViewModel() {
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
        int userId = preferenceUtil.getSession().getUserId();
        userViewModel.getUserById(userId);
        subscribers();
    }

    /**
     * Observe on live data
     */
    private void subscribers() {
        userViewModel.userLiveData.observe(getViewLifecycleOwner(), this::initProfileView);
    }

    /**
     * Populate profile data
     * @param user require user object data
     */
    private void initProfileView(User user) {
        mUserId.setText("user id: " + user.getId());
        mUsername.setText("username: " + user.getUsername());
        mEmail.setText("email: " + user.getEmail());
        if (user.getImagePath() == null) return;
        File file = new File(user.getImagePath());
        if (!file.exists()) return;
        mImageProfile.setImageURI(Uri.fromFile(file));

    }
}
