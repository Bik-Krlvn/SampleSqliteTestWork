package com.cheise_proj.local_sqlite.utils;

import com.cheise_proj.local_sqlite.model.UserLocal;

public class TestUserGenerator {
    public static UserLocal getUser() {
        return new UserLocal(
                1, "test username", "test password"
        );
    }

    public static UserLocal createNewUser() {
        return new UserLocal(
                1, "test username", "test password"
        );
    }
}
