package com.cheise_proj.domain.useCase.user;

import com.cheise_proj.domain.entity.UserEntity;
import com.cheise_proj.domain.qualifiers.Background;
import com.cheise_proj.domain.qualifiers.Foreground;
import com.cheise_proj.domain.repository.UserRepository;
import com.cheise_proj.domain.useCase.base.ObservableUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Authentication User Task, performs use case of authentication
 */
public class AuthenticateUserTask extends ObservableUseCase<UserEntity, AuthenticateUserTask.Params> {
    private UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userRepository provide user repository {@see package com.cheise_proj.domain.repository;}
     * @param background     provide rx java background scheduler
     * @param foreground     provide rx java foreground scheduler
     */
    @Inject
    public AuthenticateUserTask(UserRepository userRepository, @Background Scheduler background, @Foreground Scheduler foreground) {
        super(background, foreground);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<UserEntity> generateObservable(Params params) {
        if (params == null)
            throw new IllegalArgumentException("authenticate user params can't be empty");
        return userRepository.getAuthenticatedUser(params.username.trim(), params.password.trim());
    }

    /**
     * Authentication Param object
     */
    public class Params {
        private String username;
        private String password;

        public Params(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
