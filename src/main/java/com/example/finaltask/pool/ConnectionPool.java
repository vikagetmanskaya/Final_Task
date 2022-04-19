package com.example.finaltask.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    //8 в константу
    private static ConnectionPool instance;
    private BlockingQueue<Connection> freeConnections = new LinkedBlockingQueue<>(8);
    private BlockingQueue<Connection> usedConnections = new LinkedBlockingQueue<>(8);

      static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }

    }
    private ConnectionPool() {
//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //считывать из проперти файла
        String url = "jdbc:mysql://localhost:3306/tattoosalon";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "12345");
        for (int i = 0; i < 8; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new ExceptionInInitializerError(e);
            }
            freeConnections.add(connection);
        }
    }

    public static ConnectionPool getInstance() {
        //потокобезопасность
        //lock
        instance = new ConnectionPool();
        //unlock
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            usedConnections.remove(connection);
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            //log
            Thread.currentThread().interrupt();
        }
    }
    //method deregisterDriver
    public void destroyPool(){
        for (int i = 0; i < 8; i++) {
            try {
                freeConnections.take().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                //log
                //e.printStackTrace();
            }
        }
    }
}
