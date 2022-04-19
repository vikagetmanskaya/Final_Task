package com.example.finaltask.service;

import com.example.finaltask.entity.User;
import com.example.finaltask.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean addUser(User user) throws ServiceException;
}
