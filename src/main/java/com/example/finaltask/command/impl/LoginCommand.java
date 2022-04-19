package com.example.finaltask.command.impl;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.Router;
import com.example.finaltask.exception.CommandException;
import com.example.finaltask.exception.ServiceException;
import com.example.finaltask.service.UserService;
import com.example.finaltask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.example.finaltask.command.RequestParameterAttributeName.*;
import static com.example.finaltask.command.PagePath.*;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if(userService.authenticate(email, password)){
                request.setAttribute(USER, email);
                session.setAttribute(USER_NAME, email);
                page = MAIN_PAGE;
            }else {
                request.setAttribute(LOGIN_MESSAGE, "incorrect login or password");
                page = START_PAGE;
            }
            session.setAttribute("current_page", page);//сделать константы сессии
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
