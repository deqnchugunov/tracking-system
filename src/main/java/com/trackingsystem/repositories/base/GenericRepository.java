package com.trackingsystem.repositories.base;

import java.util.List;

public interface GenericRepository<T> {
    List<T> getAll();

    T getById(int id);

    T create(T entity);

    T update(T entity);

    T delete(T entity);
}
