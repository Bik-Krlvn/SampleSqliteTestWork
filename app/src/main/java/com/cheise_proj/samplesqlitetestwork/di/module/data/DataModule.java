package com.cheise_proj.samplesqlitetestwork.di.module.data;

import com.cheise_proj.data.mapper.UserEntityDataMapper;
import com.cheise_proj.data.mapper.base.Mapper;
import com.cheise_proj.data.model.UserData;
import com.cheise_proj.data.repository.user.UserRepositoryImpl;
import com.cheise_proj.domain.entity.UserEntity;
import com.cheise_proj.domain.repository.UserRepository;

import dagger.Binds;
import dagger.Module;

@Module(includes = {DataModule.Binders.class})
public class DataModule {
    @Module
    interface Binders{
        @Binds
        Mapper<UserEntity, UserData> bindsUserDataMapper(UserEntityDataMapper userEntityDataMapper);

        @Binds
        UserRepository bindsUserRepositoryImpl(UserRepositoryImpl userRepository);
    }
}
