package com.cheise_proj.local_sqlite.source;

import com.cheise_proj.data.model.UserData;
import com.cheise_proj.data.repository.LocalDataSource;
import com.cheise_proj.local_sqlite.db.dao.UserDao;
import com.cheise_proj.local_sqlite.mapper.user.UserDataLocalMapper;

import javax.inject.Inject;

import io.reactivex.Single;

public class LocalDataSourceImpl implements LocalDataSource {
    private UserDao userDao;
    private UserDataLocalMapper userDataLocalMapper;

    @Inject
    public LocalDataSourceImpl(UserDao userDao, UserDataLocalMapper userDataLocalMapper) {
        this.userDao = userDao;
        this.userDataLocalMapper = userDataLocalMapper;
    }


    @Override
    public Single<Integer> createUser(String username, String email, String password, String imagePath) {
        return userDao.createUser(username,email,password,imagePath);
    }

    @Override
    public Single<UserData> getAuthenticatedUser(String username, String password) {
        return userDao.getUser(username, password)
                .map(userLocal -> userDataLocalMapper.from(userLocal));
    }
}
