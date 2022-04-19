package com.example.finaltask.service.impl;

import com.example.finaltask.dao.BaseDao;
import com.example.finaltask.dao.impl.TattooDaoImpl;
import com.example.finaltask.entity.Tattoo;
import com.example.finaltask.exception.DaoException;
import com.example.finaltask.exception.ServiceException;
import com.example.finaltask.service.TattooService;

import java.util.List;

public class TattooServiceImpl implements TattooService {
    private static TattooServiceImpl instance = new TattooServiceImpl();
    private TattooServiceImpl() {
    }

    public static TattooServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Tattoo> findAllTattoos() throws ServiceException {
        BaseDao baseDao = TattooDaoImpl.getInstance();
        List<Tattoo> tattooList;
        try {
            tattooList = baseDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return tattooList;
    }
}
