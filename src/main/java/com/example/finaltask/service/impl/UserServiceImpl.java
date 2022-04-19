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
    private static UserServiceImpl instance = new UserServiceImpl();
    private CustomValidator validator = CustomValidatorImpl.getInstance();
    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String email, String password) throws ServiceException {
        //md5
        UserDao userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            if(validator.validateEmail(email) && validator.validatePassword(password)) {
                match = userDao.authenticate(email, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
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
