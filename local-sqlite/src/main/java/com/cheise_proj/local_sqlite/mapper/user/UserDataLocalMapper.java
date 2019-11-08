package com.cheise_proj.local_sqlite.mapper.user;

import com.cheise_proj.data.model.UserData;
import com.cheise_proj.local_sqlite.mapper.base.Mapper;
import com.cheise_proj.local_sqlite.model.UserLocal;

import javax.inject.Inject;

public class UserDataLocalMapper implements Mapper<UserData, UserLocal> {
    @Inject
    public UserDataLocalMapper() {
    }

    @Override
    public UserData from(UserLocal userLocal) {
        return new UserData(
                userLocal.getId(), userLocal.getUsername(), userLocal.getEmail(), userLocal.getPassword(), userLocal.getImagePath()
        );
    }

    @Override
    public UserLocal to(UserData userData) {
        return new UserLocal(userData.getId(), userData.getUsername(),userData.getEmail(), userData.getPassword(),userData.getImagePath());
    }
}
