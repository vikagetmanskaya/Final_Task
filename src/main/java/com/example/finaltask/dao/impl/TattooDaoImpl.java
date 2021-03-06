package com.example.finaltask.dao.impl;

import com.example.finaltask.dao.BaseDao;
import com.example.finaltask.entity.Tattoo;
import com.example.finaltask.exception.DaoException;
import com.example.finaltask.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.example.finaltask.dao.DataBaseColumn.*;

public class TattooDaoImpl implements BaseDao<Tattoo> {
    private static final String FIND_ALL_TATTOOS = "SELECT name, first_name_master, last_name_master, size_in_cm, date_added, rating FROM tattoos";

    private static TattooDaoImpl instance = new TattooDaoImpl();

    private TattooDaoImpl() {
    }

    public static TattooDaoImpl getInstance() {
        return instance;
    }
    @Override
    public boolean add(Tattoo tattoo) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Tattoo tattoo) throws DaoException {
        return false;
    }

    @Override
    public List<Tattoo> findAll() throws DaoException {
        List<Tattoo> tattooList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TATTOOS);
             ResultSet resultSet = statement.executeQuery()){

                while(resultSet.next()){
                    Tattoo tattoo = new Tattoo(resultSet.getString(COLUMN_NAME),
                            resultSet.getString(COLUMN_FIRST_NAME_MASTER),
                            resultSet.getString(COLUMN_LAST_NAME_MASTER),
                            resultSet.getInt(COLUMN_SIZE),
                            resultSet.getDate(COLUMN_DATE),
                            resultSet.getDouble(COLUMN_RATING));//билдер или мэппер
                    tattooList.add(tattoo);
                }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tattooList;
    }

    @Override
    public Tattoo update(Tattoo tattoo) throws DaoException {
        return null;
    }
}
