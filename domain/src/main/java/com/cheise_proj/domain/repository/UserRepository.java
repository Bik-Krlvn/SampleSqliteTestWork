package com.cheise_proj.domain.repository;

import com.cheise_proj.domain.entity.UserEntity;

import io.reactivex.Observable;

public interface UserRepository {
    /**
     * Returns created user row id
     *
     * @param username  input username
     * @param email     input email
     * @param password  input password
     * @param imagePath input image file path
     * @return observable integer of row id
     */
    Observable<Integer> createUser(String username, String email,String password ,String imagePath);

    /**
     * Returns authenticated user profile info
     *
     * @param username input username
     * @param password input password
     * @return observable userEntity
     */
    Observable<UserEntity> getAuthenticatedUser(String username, String password);

    /**
     * Returns user profile info
     * @param userId input user id
     * @return observable userEntity
     */
    Observable<UserEntity> getUserById(int userId);

}
