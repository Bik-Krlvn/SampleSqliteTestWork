package com.cheise_proj.local_sqlite.source;

import com.cheise_proj.local_sqlite.db.dao.UserDao;
import com.cheise_proj.local_sqlite.mapper.user.UserDataLocalMapper;
import com.cheise_proj.local_sqlite.model.UserLocal;
import com.cheise_proj.local_sqlite.utils.TestUserGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Single;

@RunWith(JUnit4.class)
public class LocalDataSourceImplTest {
    private LocalDataSourceImpl localDataSource;
    @Mock
    private UserDao userDao;

    private UserDataLocalMapper userDataLocalMapper = new UserDataLocalMapper();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        localDataSource = new LocalDataSourceImpl(userDao, userDataLocalMapper);
    }

    @Test
    public void Create_new_user_success() {
        UserLocal newUser = TestUserGenerator.createNewUser();
        int insertedRow = 1;
        Mockito.when(userDao.createUser(newUser.getUsername(),
                newUser.getPassword())).thenReturn(Single.just(insertedRow));
        localDataSource.createUser(newUser.getUsername(),
                newUser.getPassword()).test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertComplete();
    }

    @Test
    public void Get_user_authenticated_user_success() {
        UserLocal inputs = TestUserGenerator.getUser();
        Mockito.when(userDao.getUser(inputs.getUsername(),
                inputs.getPassword())).thenReturn(Single.just(inputs));
        localDataSource.getAuthenticatedUser(inputs.getUsername(), inputs.getPassword())
                .test()
                .assertValueCount(1)
                .assertValue(userData -> userData.getUsername().equals(userDataLocalMapper.from(inputs).getUsername()))
                .assertComplete();
    }
}