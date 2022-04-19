package com.example.finaltask.dao;

import com.example.finaltask.entity.AbstractEntity;
import com.example.finaltask.exception.DaoException;

import java.util.List;

public interface BaseDao <T extends AbstractEntity>{
    boolean add(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
    List<T> findAll() throws DaoException;
    T update(T t) throws DaoException;
}
