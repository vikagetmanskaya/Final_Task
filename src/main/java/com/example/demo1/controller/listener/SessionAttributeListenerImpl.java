package com.example.demo1.controller.listener;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger logger = LogManager.getLogger();


    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        logger.info("attributeAdded: " + sbe.getSession().getAttribute("user_name"));
        logger.info("attributeAdded: " + sbe.getSession().getAttribute("current_page"));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        logger.info("attributeReplaced: " + sbe.getSession().getAttribute("user_name"));
        logger.info("attributeReplaced: " + sbe.getSession().getAttribute("current_page"));
    }
}
