package com.example.demo1.service;

import com.example.demo1.entity.User;
import com.example.demo1.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean addUser(User user) throws ServiceException;
}
