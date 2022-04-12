package com.example.demo1.service.impl;

import com.example.demo1.dao.BaseDao;
import com.example.demo1.dao.UserDao;
import com.example.demo1.dao.impl.UserDaoImpl;
import com.example.demo1.entity.User;
import com.example.demo1.exception.DaoException;
import com.example.demo1.exception.ServiceException;
import com.example.demo1.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String email, String password) throws ServiceException {
        //validate login, pass + md5
        UserDao userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.authenticate(email, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        BaseDao userDao = UserDaoImpl.getInstance();
        boolean match;
        try {
            match = userDao.add(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
