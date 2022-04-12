package com.example.demo1.controller.listener;

import com.example.demo1.pool.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //ConnectionPool.getInstance();
        logger.info("contextInitialized: " + sce.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       // ConnectionPool.getInstance().destroyPool();
        logger.info("contextDestroyed: " + sce.getServletContext().getContextPath());
    }

}
