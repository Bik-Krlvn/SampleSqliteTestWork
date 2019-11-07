package com.cheise_proj.data.repository;

import com.cheise_proj.data.model.UserData;

import io.reactivex.Single;

public interface LocalDataSource {
    Single<Integer> createUser(String username, String email, String password, String imagePath);

    Single<UserData> getAuthenticatedUser(String username, String password);
}
