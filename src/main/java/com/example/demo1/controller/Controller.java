package com.example.demo1.controller;

import java.io.*;

import com.example.demo1.command.Command;
import com.example.demo1.command.CommandType;
import com.example.demo1.constant.PagePath;
import com.example.demo1.constant.RequestParameterAndAttribute;
import com.example.demo1.exception.CommandException;
import com.example.demo1.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    public void init() {
        ConnectionPool.getInstance();
        logger.info("Servlet init: " + this.getServletInfo());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
//String strNum = request.getParameter("num");
//int resNum = 2 * Integer.parseInt(strNum);
//request.setAttribute("result", resNum);
//        // Hello
        String commandStr = request.getParameter(RequestParameterAndAttribute.COMMAND);
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
            //response.sendRedirect("../" + page);
        } catch (CommandException e) {
            //response.sendError(500);//1
            // throw new ServletException(e);//2
            request.setAttribute("error_msg", e.getCause()); //3
            request.getRequestDispatcher(PagePath.ERROR_PAGE_500).forward(request, response); //3

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.info("Servlet destroy: " + this.getServletName());
    }
}