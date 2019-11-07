package com.cheise_proj.data.repository.user;

import com.cheise_proj.data.mapper.UserEntityDataMapper;
import com.cheise_proj.data.model.UserData;
import com.cheise_proj.data.repository.LocalDataSource;
import com.cheise_proj.data.utils.TestUserGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

@RunWith(JUnit4.class)
public class UserRepositoryImplTest {
    private UserRepositoryImpl userRepository;
    @Mock
    private LocalDataSource localDataSource;
    private UserEntityDataMapper userEntityDataMapper = new UserEntityDataMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userRepository = new UserRepositoryImpl(localDataSource, userEntityDataMapper);
    }

    @Test
    public void createUser() {
        UserData newUser = TestUserGenerator.createNewUser();
        int insertRow = 1;
        Mockito.when(localDataSource.createUser(
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getImagePath()
        )).thenReturn(Single.just(insertRow));
        userRepository.createUser(
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getImagePath()
        )
                .test()
                .assertValueCount(1)
                .assertValue(integer -> integer == insertRow)
                .assertSubscribed();

    }

    @Test
    public void getAuthenticatedUser() {
        UserData inputs = TestUserGenerator.getUser();
        Mockito.when(localDataSource
                .getAuthenticatedUser(inputs.getUsername(), inputs.getPassword()))
                .thenReturn(Single.just(inputs));

        userRepository.getAuthenticatedUser(inputs.getUsername(), inputs.getPassword())
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(userEntity -> userEntity.getId() == userEntityDataMapper.from(inputs).getId())
                .assertComplete();
    }
}