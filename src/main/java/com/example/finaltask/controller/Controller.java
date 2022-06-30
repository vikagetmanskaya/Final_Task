package com.example.finaltask.controller;

import java.io.*;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.CommandType;
import com.example.finaltask.command.Router;
import com.example.finaltask.command.RequestParameterAttributeName;
import com.example.finaltask.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    //private static final Logger logger = LogManager.getLogger();

    public void init() {
        //ConnectionPool.getInstance();
        //logger.info("Servlet init: " + this.getServletInfo());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
        //ConnectionPool.getInstance().destroyPool();
        //logger.info("Servlet destroy: " + this.getServletName());
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");//в фильтры
        String commandType = request.getParameter(RequestParameterAttributeName.COMMAND);
        Command command = CommandType.define(commandType);
        try {
            Router router = command.execute(request);
            switch (router.getType()){
                case FORWARD:
                    request.getRequestDispatcher(router.getPage()).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(request.getContextPath() + router.getPage());
                   break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (CommandException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }
}