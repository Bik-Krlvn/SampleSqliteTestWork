package com.cheise_proj.domain.utils;

import com.cheise_proj.domain.entity.UserEntity;

/**
 * Test User Generator util class, use for testing domain use case
 */
public class TestUserGenerator {
    public static UserEntity getUser() {
        return new UserEntity(
                1, "test username", "test email", "test password", "test file path"
        );
    }

    public static UserEntity createNewUser() {
        return new UserEntity(
                1, "test username", "test email", "test password", "test file path"
        );
    }
}
