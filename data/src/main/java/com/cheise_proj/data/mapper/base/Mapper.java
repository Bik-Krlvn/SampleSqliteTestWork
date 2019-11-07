package com.cheise_proj.data.mapper.base;

/**
 * Base Mapper for data layer, maps model to respective layer
 *
 * @param <T> provide type domain model layer
 * @param <E> provide type data model layer
 */
public interface Mapper<T, E> {
    /**
     * Maps from user data model to domain user entity
     *
     * @param e type user data model
     * @return domain user entity
     */
    T from(E e);

    /**
     * Maps from domain user entity to user data model
     *
     * @param t type domain user entity
     * @return user data model
     */
    E to(T t);
}
