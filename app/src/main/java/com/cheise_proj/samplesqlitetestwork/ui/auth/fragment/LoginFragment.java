package com.cheise_proj.samplesqlitetestwork.ui.auth.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.cheise_proj.presentation.model.User;
import com.cheise_proj.presentation.viewmodel.UserViewModel;
import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseFragment;
import com.cheise_proj.samplesqlitetestwork.ui.main.NavigationActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private UserViewModel userViewModel;
    private EditText etUsername;
    private EditText etPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLogin = view.findViewById(R.id.btn_login);
        TextView btnRegister = view.findViewById(R.id.btn_register);
        etPassword = view.findViewById(R.id.et_password);
        etUsername = view.findViewById(R.id.et_username);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureViewModel();
    }

    private void configureViewModel() {
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
        subscribers();
    }

    private void subscribers() {
        userViewModel.userLiveData.observe(getViewLifecycleOwner(), user -> {
            if (user.getId() < 1) {
                Toast.makeText(getContext(), "username or password invalid", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getContext(), "welcome: " + user.getUsername(), Toast.LENGTH_SHORT).show();
            navigateToMainPage(user);
        });
    }


    private void navigateToRegisterFragment() {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_socket, RegisterFragment.newInstance())
                .commit();

    }

    private void prepareToAuthenticate() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("provide username");
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("provide a password");
        } else {
            userViewModel.authenticateUser(username, password);
        }

    }

    private void navigateToMainPage(User user) {
        preferenceUtil.setLoginInfo(true, user.getId());
        Intent navIntent = new Intent(getContext(), NavigationActivity.class);
        startActivity(navIntent);
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                prepareToAuthenticate();
                break;
            case R.id.btn_register:
                navigateToRegisterFragment();
                break;
            default:
                break;
        }
    }


}
