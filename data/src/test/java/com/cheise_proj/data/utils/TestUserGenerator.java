package com.cheise_proj.data.utils;

import com.cheise_proj.data.model.UserData;

/**
 * Test User Generator for userRepositoryImpl from domain layer
 */
public class TestUserGenerator {

    public static UserData getUser() {
        return new UserData(
                1, "test username", "test email", "test password", "test file path"
        );
    }

    public static UserData createNewUser() {
        return new UserData(
                1, "test username", "test email", "test password", "test file path"
        );
    }
}
