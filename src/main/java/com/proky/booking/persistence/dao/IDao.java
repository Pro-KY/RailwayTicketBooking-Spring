package com.proky.booking.persistence.dao;

import java.util.Optional;

public interface IDao<T> {
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    Optional<T> findById(Long id);
}
