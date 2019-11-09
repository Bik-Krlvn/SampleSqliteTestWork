package com.cheise_proj.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cheise_proj.domain.useCase.user.AuthenticateUserTask;
import com.cheise_proj.domain.useCase.user.GetUserByIdTask;
import com.cheise_proj.domain.useCase.user.RegisterUserTask;
import com.cheise_proj.presentation.mapper.UserEntityMapper;
import com.cheise_proj.presentation.model.User;
import com.cheise_proj.presentation.viewmodel.base.BaseViewModel;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * UserViewModel class, provides ui layer with specified data
 */
public class UserViewModel extends BaseViewModel {
    private AuthenticateUserTask authenticateUserTask;
    private UserEntityMapper userEntityMapper;
    private RegisterUserTask registerUserTask;
    private GetUserByIdTask getUserByIdTask;
    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    public LiveData<User> userLiveData = userMutableLiveData;
    private MutableLiveData<Integer> _insertedRow = new MutableLiveData<>();
    public LiveData<Integer> insertedRow = _insertedRow;

    /**
     * Constructor
     *
     * @param authenticateUserTask provide authentication use case from domain layer
     * @param userEntityMapper     provide user entity mapper
     * @param registerUserTask     provide registration use case from domain layer
     */
    @Inject
    public UserViewModel(AuthenticateUserTask authenticateUserTask, UserEntityMapper userEntityMapper, RegisterUserTask registerUserTask, GetUserByIdTask getUserByIdTask) {
        this.authenticateUserTask = authenticateUserTask;
        this.userEntityMapper = userEntityMapper;
        this.registerUserTask = registerUserTask;
        this.getUserByIdTask = getUserByIdTask;
    }

    /**
     * Authenticate user, get provided profile base on credentials
     *
     * @param username input username
     * @param password input password
     */
    public void authenticateUser(String username, String password) {
        disposable.add(authenticateUserTask
                .buildCase(authenticateUserTask.new Params(username, password))
                .map(userEntity -> {
                    Logger.getLogger("authenticateUser")
                            .log(Level.INFO, "username: " + userEntity.getUsername());
                    return userEntityMapper.to(userEntity);
                })
                .subscribe(user -> userMutableLiveData.setValue(user))
        );
    }

    /**
     * Create a new User
     *
     * @param user provide user model
     */
    public void createNewUser(User user) {
        disposable.add(
                registerUserTask.buildCase(registerUserTask.new Params(
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getImagePath()
                ))
                        .map(integer -> {
                            Logger.getLogger("createNewUser").log(Level.INFO, "insertedRow: " + integer);
                            return integer;
                        })
                        .doOnError(throwable -> Logger.getLogger("createNewUser").log(Level.INFO, "error: " + throwable.getLocalizedMessage()))
                        .subscribe(integer -> {
                            _insertedRow.setValue(integer);
                        })
        );
    }

    public void getUserById(int userId){
        disposable.add(getUserByIdTask.buildCase(userId)
                .map(userEntity -> userEntityMapper.to(userEntity))
                .subscribe(user -> userMutableLiveData.setValue(user)));
    }


}
