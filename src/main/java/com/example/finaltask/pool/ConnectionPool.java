package com.example.finaltask.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    //8 в константу
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private BlockingQueue<ProxyConnection> freeConnections = new LinkedBlockingQueue<>(8);
    private BlockingQueue<ProxyConnection> usedConnections = new LinkedBlockingQueue<>(8);

      static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }

    }
    private ConnectionPool() {
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
            ProxyConnection proxyConnection = new ProxyConnection(connection);
            freeConnections.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
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

        if (connection instanceof ProxyConnection proxyConnection) {
            try {
                usedConnections.remove(proxyConnection);
                freeConnections.put(proxyConnection);

            } catch (InterruptedException e) {
                //log
                Thread.currentThread().interrupt();
            }
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
