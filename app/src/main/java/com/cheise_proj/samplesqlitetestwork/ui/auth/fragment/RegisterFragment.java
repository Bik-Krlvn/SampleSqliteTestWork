package com.cheise_proj.samplesqlitetestwork.ui.auth.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cheise_proj.presentation.model.User;
import com.cheise_proj.presentation.viewmodel.UserViewModel;
import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseFragment;
import com.cheise_proj.samplesqlitetestwork.utils.RealPathUtil;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener {
    private UserViewModel userViewModel;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private ImageView imgProfile;
    private static final int PICTURE_RESULT = 100;
    private String imagePath;
    public static final int REQUEST_PERMISSION = 203;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RegisterFragment.
     */
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etPassword = view.findViewById(R.id.et_password);
        etUsername = view.findViewById(R.id.et_username);
        etEmail = view.findViewById(R.id.et_email);
        TextView btnLogin = view.findViewById(R.id.btn_login);
        imgProfile = view.findViewById(R.id.img_profile);
        Button btnRegister = view.findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        imgProfile.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configViewModel();
    }

    private void configViewModel() {
        userViewModel = new ViewModelProvider(this, viewModelFactory).get(UserViewModel.class);
        userViewModel.insertedRow.observe(getViewLifecycleOwner(), integer -> {
            if (integer > 0) {
                Toast.makeText(getContext(), "registration successful", Toast.LENGTH_SHORT).show();
                navigateToLoginFragment();
            } else {
                etUsername.setError("username already taken");
                Toast.makeText(getContext(), "registration failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareToRegister() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("provide username");
        }

        if (TextUtils.isEmpty(email)) {
            etUsername.setError("invalid email");
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("provide a password");
        } else {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setImagePath(imagePath);
            userViewModel.createNewUser(newUser);
        }

    }

    private void navigateToLoginFragment() {
        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_socket, LoginFragment.newInstance())
                .commit();
    }

    private void openGallery() {
        Intent pickImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickImageIntent.setType("image/jpeg");
        pickImageIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(pickImageIntent, "Insert Picture"), PICTURE_RESULT);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                navigateToLoginFragment();
                break;
            case R.id.btn_register:
                prepareToRegister();
                break;
            case R.id.img_profile:
                checkPermission();
                break;
            default:
                break;
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showDialogExplain();
            } else {
                ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            }
        } else {
            openGallery();
        }
    }

    private void showDialogExplain() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Permission Alert");
        builder.setMessage("App needs storage permission to show gallery files");
        builder.setPositiveButton("Allow", (dialog, which) ->
                ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION));
        builder.setNegativeButton("Cancel", null);
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == Activity.RESULT_OK) {
            assert data != null;
            if (data.getData() != null) {
                imgProfile.setImageURI(data.getData());
                Logger.getLogger("image").log(Level.INFO, "uri: " + data.getData());

                imagePath = RealPathUtil.getRealPath(Objects.requireNonNull(getContext()), data.getData());
                Logger.getLogger("image").log(Level.INFO, "filePath: " + imagePath);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }
}
