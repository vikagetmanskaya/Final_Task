package com.example.finaltask.controller.listener;

import com.example.finaltask.pool.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
        logger.info("contextInitialized: " + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroyPool();
        logger.info("contextDestroyed: " + sce.getServletContext().getContextPath());
    }

}
