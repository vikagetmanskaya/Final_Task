package com.example.demo1.dao;

import com.example.demo1.exception.DaoException;

public interface UserDao {
    boolean authenticate(String email, String password) throws DaoException;
}
