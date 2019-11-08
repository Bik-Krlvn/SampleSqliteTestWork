package com.cheise_proj.samplesqlitetestwork.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseActivity;
import com.cheise_proj.samplesqlitetestwork.utils.Constants;

import java.io.File;

/**
 * Shows the profile info of logged in user
 */
public class MainActivity extends BaseActivity {
    private TextView mUserId;
    private TextView mUsername;
    private TextView mEmail;
    private ImageView mImageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserId = findViewById(R.id.tv_user_id);
        mUsername = findViewById(R.id.tv_username);
        mEmail = findViewById(R.id.tv_email);
        mImageProfile = findViewById(R.id.img_profile);
        Intent bundle = getIntent();
        if (bundle.hasExtra(Constants.USER_ID_EXTRA)) {
            initProfileView(bundle);
        }
    }

    private void initProfileView(Intent bundle) {
        int userId = bundle.getIntExtra(Constants.USER_ID_EXTRA, -1);
        String username = bundle.getStringExtra(Constants.LOGIN_USERNAME_EXTRA);
        String email = bundle.getStringExtra(Constants.LOGIN_EMAIL_EXTRA);
        String imagePath = bundle.getStringExtra(Constants.LOGIN_IMAGE_PATH_EXTRA);
        mEmail.setText(email);
        mUserId.setText("user id: " + userId);
        mUsername.setText("username: " + username);
        mEmail.setText("email: "+email);
        if (imagePath == null) return;
        File file = new File(imagePath);
        if (!file.exists()) return;
        mImageProfile.setImageURI(Uri.fromFile(file));
    }

}
