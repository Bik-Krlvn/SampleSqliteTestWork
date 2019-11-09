package com.cheise_proj.local_sqlite.db.dao;

import com.cheise_proj.local_sqlite.db.AppLocalDatabase;
import com.cheise_proj.local_sqlite.model.UserLocal;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * UserDao provides abstraction layer to local sqLite db
 */
public class UserDaoImpl implements UserDao {
    private AppLocalDatabase appLocalDatabase;

    /**
     * Constructor
     *
     * @param appLocalDatabase require local db
     */
    @Inject
    public UserDaoImpl(AppLocalDatabase appLocalDatabase) {
        this.appLocalDatabase = appLocalDatabase;
    }


    @Override
    public Single<Integer> createUser(String username, String email, String password, String imagePath) {
        return Single.just(appLocalDatabase.insertUser(username, email, password, imagePath));
    }

    @Override
    public Single<UserLocal> getUser(String username, String password) {
        return Single.just(appLocalDatabase.getUser(username, password));
    }

    @Override
    public Single<UserLocal> getUserById(int userId) {
        return Single.just(appLocalDatabase.getUserById(userId));
    }
}
