package com.example.finaltask.dao.impl;

import com.example.finaltask.dao.BaseDao;
import com.example.finaltask.dao.UserDao;
import com.example.finaltask.entity.User;
import com.example.finaltask.exception.DaoException;
import com.example.finaltask.pool.ConnectionPool;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements BaseDao<User>, UserDao {
    public static final String COLUMN_PASSWORD = "password";
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE email = ?";
    private static final String INSERT_USER = "INSERT INTO `tattoosalon`.`users` ( `email`, `password`, `first_name`, `last_name`, `date_of_birth`) VALUES (?, ?, ?, ?, ?)";
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean add(User user) throws DaoException {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setDate(5, (user.getDateOfBirth()));
            int row = statement.executeUpdate();
            if(row == 1){
                match = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean authenticate(String email, String password) throws DaoException {

        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, email);
            try(ResultSet resultSet = statement.executeQuery()) {
                String passFromDb;
                if (resultSet.next()) {
                    passFromDb = resultSet.getString(COLUMN_PASSWORD);
                    match = password.equals(passFromDb);
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return match;
    }
}
