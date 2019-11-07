package com.cheise_proj.domain.useCase.user;

import com.cheise_proj.domain.entity.UserEntity;
import com.cheise_proj.domain.repository.UserRepository;
import com.cheise_proj.domain.utils.TestUserGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Register user use case test
 */
@RunWith(JUnit4.class)
public class RegisterUserTaskTest {
    private RegisterUserTask registerUserTask;
    @Mock
    private UserRepository userRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registerUserTask = new RegisterUserTask
                (userRepository, Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void Register_new_user_success() {
        UserEntity newUser = TestUserGenerator.createNewUser();
        int insertedRow = 1;
        Mockito.when(userRepository.createUser(
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getImagePath()
        )).thenReturn(Observable.just(insertedRow));

        registerUserTask.buildCase(registerUserTask.new Params(
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getImagePath()))
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(insertedRow)
                .assertComplete();


    }

    @Test
    public void Register_new_user_failed() {
        String errorMsg = "insert action exception";
        UserEntity newUser = TestUserGenerator.createNewUser();
        Mockito.when(userRepository.createUser(
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getImagePath())
        ).thenReturn(Observable.error(new Throwable(errorMsg)));
        registerUserTask.buildCase(registerUserTask.new Params(
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getPassword(),
                newUser.getImagePath()
        )).test()
                .assertSubscribed()
                .assertError(throwable -> throwable.getLocalizedMessage().equals(errorMsg))
                .assertNotComplete();

    }

    @Test(expected = IllegalArgumentException.class)
    public void Register_new_user_with_no_data() {
        registerUserTask.buildCase(null);
    }
}