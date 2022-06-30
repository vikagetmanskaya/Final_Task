package com.example.finaltask.service;

import com.example.finaltask.entity.User;
import com.example.finaltask.exception.ServiceException;

public interface UserService {
    User authenticate(String email, String password) throws ServiceException;
    boolean addUser(User user) throws ServiceException;
}
