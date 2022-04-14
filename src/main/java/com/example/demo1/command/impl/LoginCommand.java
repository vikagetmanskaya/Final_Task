package com.example.demo1.command.impl;

import com.example.demo1.command.Command;
import com.example.demo1.constant.PagePath;
import com.example.demo1.constant.RequestParameterAndAttribute;
import com.example.demo1.exception.CommandException;
import com.example.demo1.exception.ServiceException;
import com.example.demo1.service.UserService;
import com.example.demo1.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParameterAndAttribute.EMAIL);
        String password = request.getParameter(RequestParameterAndAttribute.PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if(userService.authenticate(email, password)){
                request.setAttribute(RequestParameterAndAttribute.USER, email);
                session.setAttribute(RequestParameterAndAttribute.USER_NAME, email);
                page = PagePath.MAIN_PAGE;
            }else {
                request.setAttribute(RequestParameterAndAttribute.LOGIN_MESSAGE, "incorrect login or password");
                page = PagePath.START_PAGE;
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
