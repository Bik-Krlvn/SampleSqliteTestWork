package com.cheise_proj.presentation.mapper.base;

/**
 * Maps domain user entity to presenter user model
 *
 * @param <T> provide user entity model from domain layer
 * @param <E> provide user model from presenter layer
 */
public interface Mapper<T, E> {

    /**
     * from presenter model to user entity
     *
     * @param e provide user model from presenter
     * @return user entity model
     */
    T from(E e);

    /**
     * to user model in presenter layer
     *
     * @param t provide user entity from domain layer
     * @return user model
     */
    E to(T t);
}
