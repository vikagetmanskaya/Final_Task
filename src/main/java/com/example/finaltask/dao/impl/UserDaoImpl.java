package com.example.finaltask.dao.impl;

import com.example.finaltask.dao.BaseDao;
import com.example.finaltask.dao.UserDao;
import com.example.finaltask.entity.User;
import com.example.finaltask.entity.UserRole;
import com.example.finaltask.exception.DaoException;
import com.example.finaltask.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import static com.example.finaltask.dao.DataBaseColumn.*;

public class UserDaoImpl implements BaseDao<User>, UserDao {
    private static final String SELECT_LOGIN_PASSWORD = "SELECT first_name, last_name, date_of_birth, " +
            "user_role FROM users WHERE email = ? AND password = ?";
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
            statement.setDate(5, (Date) user.getDateOfBirth());
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
    public User authenticate(String email, String password) throws DaoException {

        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    user = new User.UserBuilder().setEmail(email)
                            .setFirstName(resultSet.getString(1))
                            .setLastName(resultSet.getString(2))
                            .setDateOfBirth(resultSet.getDate(3))
                            .setRole(UserRole.valueOf(resultSet.getString(4))).getUser();
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }
}
