package com.cheise_proj.domain.useCase.user;

import com.cheise_proj.domain.entity.UserEntity;
import com.cheise_proj.domain.qualifiers.Background;
import com.cheise_proj.domain.qualifiers.Foreground;
import com.cheise_proj.domain.repository.UserRepository;
import com.cheise_proj.domain.useCase.base.ObservableUseCase;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * GetUserById class
 */
public class GetUserByIdTask extends ObservableUseCase<UserEntity, Integer> {
    private UserRepository userRepository;

    /**
     * Constructor
     *
     * @param userRepository provide user repository {@see package com.cheise_proj.domain.repository;}
     * @param background     provide rx java background scheduler
     * @param foreground     provide rx java foreground scheduler
     */
    @Inject
    public GetUserByIdTask(UserRepository userRepository, @Background Scheduler background, @Foreground Scheduler foreground) {
        super(background, foreground);
        this.userRepository = userRepository;
    }

    @Override
    protected Observable<UserEntity> generateObservable(@Nullable Integer integer) {
        if (integer == null)
            throw new IllegalArgumentException("user id can't be empty");
        return userRepository.getUserById(integer);
    }
}
