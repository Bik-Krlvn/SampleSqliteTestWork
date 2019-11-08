package com.cheise_proj.local_sqlite.mapper.base;

/**
 * Maps from user data from data layer to user local model
 *
 * @param <T> provide user data model
 * @param <E> provide user local model
 */
public interface Mapper<T, E> {
    /**
     * Map from user local to user data model
     *
     * @param e provide user local model
     * @return user data model
     */
    T from(E e);

    /**
     * Map to user local model in local-sqlite layer
     *
     * @param t provide user data model
     * @return user local model
     */
    E to(T t);

}
