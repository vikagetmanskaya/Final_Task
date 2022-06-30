package com.example.finaltask.service.impl;

import com.example.finaltask.dao.BaseDao;
import com.example.finaltask.dao.UserDao;
import com.example.finaltask.dao.impl.UserDaoImpl;
import com.example.finaltask.entity.User;
import com.example.finaltask.exception.DaoException;
import com.example.finaltask.exception.ServiceException;
import com.example.finaltask.service.UserService;
import com.example.finaltask.validator.CustomValidator;
import com.example.finaltask.validator.impl.CustomValidatorImpl;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private CustomValidator validator = CustomValidatorImpl.getInstance();
    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if(instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User authenticate(String email, String password) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        User user = new User();
        try {
            if(validator.validateEmail(email) && validator.validatePassword(password)) {
                user = userDao.authenticate(email, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        BaseDao userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            if (validator.validateEmail(user.getEmail()) && validator.validatePassword(user.getPassword()) &&
            validator.validateName(user.getFirstName(), user.getLastName())){
                match = userDao.add(user);
        }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
