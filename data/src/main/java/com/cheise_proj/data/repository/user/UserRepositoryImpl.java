package com.cheise_proj.data.repository.user;

import com.cheise_proj.data.mapper.UserEntityDataMapper;
import com.cheise_proj.data.repository.LocalDataSource;
import com.cheise_proj.domain.entity.UserEntity;
import com.cheise_proj.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * UserRepository class, performs actions on available persistence storage
 * this class implements UserRepository from domain layer {@see package com.cheise_proj.domain.repository }
 */
public class UserRepositoryImpl implements UserRepository {
    private LocalDataSource localDataSource;
    private UserEntityDataMapper userEntityDataMapper;

    /**
     * Constructor
     *
     * @param localDataSource      provide local data source
     * @param userEntityDataMapper provide user entity mapper
     */
    @Inject
    public UserRepositoryImpl(LocalDataSource localDataSource, UserEntityDataMapper userEntityDataMapper) {
        this.localDataSource = localDataSource;
        this.userEntityDataMapper = userEntityDataMapper;
    }


    @Override
    public Observable<Integer> createUser(String username, String email, String password, String imagePath) {
        return localDataSource.createUser(username, email, password, imagePath).toObservable();
    }

    @Override
    public Observable<UserEntity> getAuthenticatedUser(String username, String password) {
        return localDataSource.getAuthenticatedUser(username, password)
                .map(userData -> userEntityDataMapper.from(userData))
                .toObservable();
    }

    @Override
    public Observable<UserEntity> getUserById(int userId) {
        return localDataSource.getUserById(userId)
                .map(userData -> userEntityDataMapper.from(userData))
                .toObservable();
    }
}
