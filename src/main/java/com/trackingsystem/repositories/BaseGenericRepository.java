package com.trackingsystem.repositories;

import java.util.List;

public interface BaseGenericRepository<T> {
    List<T> getAll();

    T getById(int id);

    T create(T entity);

    T update(T entity);

    T delete(T entity);
}
