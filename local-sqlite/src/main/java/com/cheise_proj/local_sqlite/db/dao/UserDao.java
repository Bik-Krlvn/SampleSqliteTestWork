package com.cheise_proj.local_sqlite.db.dao;

import com.cheise_proj.local_sqlite.model.UserLocal;

import io.reactivex.Single;

public interface UserDao {
    Single<Integer> createUser(String username, String email, String password,String imagePath);

    Single<UserLocal> getUser(String username, String password);

}
