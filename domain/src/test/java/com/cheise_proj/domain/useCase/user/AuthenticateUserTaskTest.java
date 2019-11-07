package com.cheise_proj.domain.useCase.user;

import com.cheise_proj.domain.entity.UserEntity;
import com.cheise_proj.domain.repository.UserRepository;
import com.cheise_proj.domain.utils.TestUserGenerator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Authentication use case  test
 */
public class AuthenticateUserTaskTest {
    private AuthenticateUserTask authenticateUserTask;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticateUserTask = new AuthenticateUserTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline());
    }

    @Test
    public void Authenticate_user_with_credentials_success() {
        UserEntity input = TestUserGenerator.getUser();
        Mockito.when(userRepository
                .getAuthenticatedUser(input.getUsername(), input.getPassword()))
                .thenReturn(Observable.just(input));
        AuthenticateUserTask.Params params = authenticateUserTask.new Params(input.getUsername(), input.getPassword());
        authenticateUserTask.buildCase(params)
                .test()
                .assertSubscribed()
                .assertValueCount(1)
                .assertValue(userEntity -> userEntity == input)
                .assertComplete();
    }

    @Test
    public void Authenticate_user_with_credentials_failed() {
        String errorMsg = "retrieve user exception";
        UserEntity input = TestUserGenerator.getUser();
        Mockito.when(userRepository
                .getAuthenticatedUser(input.getUsername(), input.getPassword()))
                .thenReturn(Observable.error(new Throwable(errorMsg)));
        AuthenticateUserTask.Params params = authenticateUserTask.new Params(input.getUsername(), input.getPassword());
        authenticateUserTask.buildCase(params)
                .test()
                .assertSubscribed()
                .assertError(throwable -> throwable.getLocalizedMessage().equals(errorMsg))
                .assertNotComplete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void Authenticate_user_with_no_params_throws_exception() {
        authenticateUserTask.buildCase(null);
    }
}