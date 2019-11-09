package com.cheise_proj.samplesqlitetestwork.utils;

import android.content.SharedPreferences;

import com.cheise_proj.samplesqlitetestwork.ui.model.SessionUser;

import javax.inject.Inject;

public class PreferenceUtil {
    private SharedPreferences sharedPreferences;

    @Inject
    public PreferenceUtil(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setLoginInfo(Boolean isLogin, int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.LOGIN_STATUS_PREF, isLogin);
        editor.putInt(Constants.LOGIN_USER_ID_PREF, userId);
        editor.apply();
    }

    public SessionUser getSession() {
        boolean status = sharedPreferences.getBoolean(Constants.LOGIN_STATUS_PREF, false);
        int userId = sharedPreferences.getInt(Constants.LOGIN_USER_ID_PREF, 0);
        return new SessionUser(status, userId);
    }

}
