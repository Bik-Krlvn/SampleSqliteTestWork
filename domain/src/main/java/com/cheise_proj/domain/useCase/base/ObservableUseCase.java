package com.cheise_proj.domain.useCase.base;



import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Base Observable use case
 * @param <T> type any
 * @param <Input> type any
 */
public abstract class ObservableUseCase<T, Input> {

    private Scheduler background;
    private Scheduler foreground;

    /**
     * Constructor
     * @param background provide rx java background scheduler
     * @param foreground provide rxj ava main thread scheduler
     */
    public ObservableUseCase(Scheduler background, Scheduler foreground) {
        this.background = background;
        this.foreground = foreground;
    }

    protected abstract Observable<T> generateObservable(@Nullable Input input);

    /**
     * BuildCase
     * @param input type any
     * @return rx java observable of type T
     */
    public Observable<T> buildCase(@Nullable Input input) {
        return generateObservable(input)
                .subscribeOn(background)
                .observeOn(foreground);
    }


}
