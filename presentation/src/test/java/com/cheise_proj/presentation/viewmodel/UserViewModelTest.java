package com.cheise_proj.presentation.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cheise_proj.domain.repository.UserRepository;
import com.cheise_proj.domain.useCase.user.AuthenticateUserTask;
import com.cheise_proj.domain.useCase.user.GetUserByIdTask;
import com.cheise_proj.domain.useCase.user.RegisterUserTask;
import com.cheise_proj.presentation.mapper.UserEntityMapper;
import com.cheise_proj.presentation.model.User;
import com.cheise_proj.presentation.utils.TestUserGenerator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.times;

/**
 * User View Model Test
 */
@RunWith(JUnit4.class)
public class UserViewModelTest {
    private UserViewModel userViewModel;
    @Mock
    private UserRepository userRepository;
    private UserEntityMapper userEntityMapper = new UserEntityMapper();


    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        AuthenticateUserTask authenticateUserTask = new AuthenticateUserTask
                (userRepository, Schedulers.trampoline(), Schedulers.trampoline());
        RegisterUserTask registerUserTask = new RegisterUserTask(
                userRepository, Schedulers.trampoline(), Schedulers.trampoline());
        GetUserByIdTask getUserByIdTask = new GetUserByIdTask(userRepository, Schedulers.trampoline(), Schedulers.trampoline());
        userViewModel = new UserViewModel(authenticateUserTask, userEntityMapper, registerUserTask,getUserByIdTask);
    }

    @Test
    public void getAuthenticatedUser() {

        User output = TestUserGenerator.getUser();
        Mockito.when(userRepository.getAuthenticatedUser(output.getUsername(), output.getPassword()))
                .thenReturn(Observable.just(userEntityMapper.from(output)));
        userViewModel.authenticateUser(output.getUsername(), output.getPassword());
        Mockito.verify(userRepository, times(1)).getAuthenticatedUser(output.getUsername(), output.getPassword());
    }


    @Test
    public void createNewUser() {
        int insertRow = 1;
        User inputs = TestUserGenerator.createNewUser();
        Mockito.when(userRepository.createUser(
                inputs.getUsername(),
                inputs.getEmail(),
                inputs.getPassword(),
                inputs.getImagePath()
        ))
                .thenReturn(Observable.just(insertRow));

        userViewModel.createNewUser(inputs);
        Mockito.verify(userRepository, times(1)).createUser(
                inputs.getUsername(),
                inputs.getEmail(),
                inputs.getPassword(),
                inputs.getImagePath()
        );

    }
}