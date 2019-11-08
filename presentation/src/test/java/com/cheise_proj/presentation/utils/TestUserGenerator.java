package com.cheise_proj.presentation.utils;

import com.cheise_proj.presentation.model.User;

/**
 * Test User Generator, for presentation layer
 */
public class TestUserGenerator {
    public static User getUser() {
        return new User(
                1, "test username", "test email", "test password", "test file path"
        );
    }

    public static User createNewUser() {
        return new User(
                1, "test username", "test email", "test password", "test file path"
        );
    }
}
