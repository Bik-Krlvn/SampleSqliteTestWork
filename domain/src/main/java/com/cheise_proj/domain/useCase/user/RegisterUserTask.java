package com.cheise_proj.domain.useCase.user;

import com.cheise_proj.domain.qualifiers.Background;
import com.cheise_proj.domain.qualifiers.Foreground;
import com.cheise_proj.domain.repository.UserRepository;
import com.cheise_proj.domain.useCase.base.ObservableUseCase;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Register User Task, Performs registration use case
 */
public class RegisterUserTask extends ObservableUseCase<Integer, RegisterUserTask.Params> {
    private UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository provide user repository {@see package com.cheise_proj.domain.repository; }
     * @param background rx java background thread scheduler
     * @param foreground rx java main thread scheduler
     */
    @Inject
    public RegisterUserTask(UserRepository userRepository, @Background Scheduler background, @Foreground Scheduler foreground) {
        super(background, foreground);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<Integer> generateObservable(@Nullable Params params) {
        if (params == null) throw new IllegalArgumentException("user input can't be null");
        return userRepository.createUser(params.username, params.email, params.password, params.imagePath);
    }


    /**
     * Register object
     */
    public class Params {
        private String username;
        private String email;
        private String password;
        private String imagePath;

        /**
         * Constructor
         * @param username input username
         * @param email input email address
         * @param password input password
         * @param imagePath input image file path
         */
        public Params(String username, String email, String password, String imagePath) {
            this.username = username;
            this.email = email;
            this.password = password;
            this.imagePath = imagePath;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
