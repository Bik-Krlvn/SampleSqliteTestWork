package com.cheise_proj.data.mapper;

import com.cheise_proj.data.mapper.base.Mapper;
import com.cheise_proj.data.model.UserData;
import com.cheise_proj.domain.entity.UserEntity;

import javax.inject.Inject;

/**
 * User Entity Data Mapper, maps to either domain model or from data model
 * class implement base mapper {@see package com.cheise_proj.data.mapper.base.Mapper}
 */
public class UserEntityDataMapper implements Mapper<UserEntity, UserData> {
    /**
     * Constructor
     */
    @Inject
    public UserEntityDataMapper() {
    }

    @Override
    public UserEntity from(UserData userData) {
        return new UserEntity(
                userData.getId(),
                userData.getUsername(),
                userData.getEmail(),
                userData.getPassword(),
                userData.getImagePath()
        );
    }

    @Override
    public UserData to(UserEntity userEntity) {
        return new UserData(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getImagePath()
        );
    }


}
