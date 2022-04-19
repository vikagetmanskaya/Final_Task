package com.example.finaltask.dao;

import com.example.finaltask.exception.DaoException;

public interface UserDao {
    boolean authenticate(String email, String password) throws DaoException;
}
