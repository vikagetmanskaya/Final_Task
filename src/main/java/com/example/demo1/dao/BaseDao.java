package com.example.demo1.dao;

import com.example.demo1.entity.AbstractEntity;
import com.example.demo1.exception.DaoException;

import java.util.List;

public interface BaseDao <T extends AbstractEntity>{
    boolean add(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
    List<T> findAll() throws DaoException;
    T update(T t) throws DaoException;
}
