package com.cheise_proj.samplesqlitetestwork.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.auth.fragment.LoginFragment;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseActivity;
import com.cheise_proj.samplesqlitetestwork.ui.main.NavigationActivity;

/**
 * Shows login / register fragment ui
 */
public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        boolean loginStatus = preferenceUtil.getSession().isLoginStatus();
        if (!loginStatus) {
            initLoginFragment();
            return;
        }
        navigateToMainPage();

    }

    private void navigateToMainPage() {
        startActivity(new Intent(this, NavigationActivity.class));
        finish();
    }

    /**
     * Start with login fragment
     */
    private void initLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_socket, LoginFragment.newInstance())
                .commit();
    }
}
