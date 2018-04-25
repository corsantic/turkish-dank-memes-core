package com.turkishdank.turkishdankmemes.dao;

import java.util.List;

public interface AbstractDAO<T>
{
    void save(T t);

    T getById(String id);

    List<T> loadAll();

    void deleteAll();
}