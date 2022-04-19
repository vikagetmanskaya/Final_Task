package com.example.finaltask.service;

import com.example.finaltask.entity.Tattoo;
import com.example.finaltask.exception.ServiceException;

import java.util.List;

public interface TattooService {
    List<Tattoo> findAllTattoos() throws ServiceException;
}
