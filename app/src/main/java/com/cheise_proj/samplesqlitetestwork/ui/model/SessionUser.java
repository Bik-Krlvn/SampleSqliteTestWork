package com.cheise_proj.samplesqlitetestwork.ui.model;

/**
 * Session User object
 */
public class SessionUser {
    private boolean loginStatus;
    private int userId;

    public SessionUser() {
    }

    public SessionUser(boolean loginStatus, int userId) {
        this.loginStatus = loginStatus;
        this.userId = userId;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
