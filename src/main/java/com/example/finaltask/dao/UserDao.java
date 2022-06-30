package com.example.finaltask.dao;

import com.example.finaltask.entity.User;
import com.example.finaltask.exception.DaoException;

public interface UserDao {
    User authenticate(String email, String password) throws DaoException;
}
