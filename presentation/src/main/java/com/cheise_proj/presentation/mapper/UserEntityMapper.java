package com.cheise_proj.presentation.mapper;

import com.cheise_proj.domain.entity.UserEntity;
import com.cheise_proj.presentation.mapper.base.Mapper;
import com.cheise_proj.presentation.model.User;

import javax.inject.Inject;

/**
 * User Entity Mapper, maps either user entity or user model
 * class implement base mapper {@see package com.cheise_proj.data.mapper.base.Mapper}
 */
public class UserEntityMapper implements Mapper<UserEntity, User> {
    /**
     * Constructor
     */
    @Inject
    public UserEntityMapper() {
    }

    @Override
    public UserEntity from(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getImagePath()
        );
    }

    @Override
    public User to(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getImagePath()
        );
    }
}
