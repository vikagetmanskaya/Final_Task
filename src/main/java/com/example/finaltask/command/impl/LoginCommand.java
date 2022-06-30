package com.example.finaltask.command.impl;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.Router;
import com.example.finaltask.entity.User;
import com.example.finaltask.exception.CommandException;
import com.example.finaltask.exception.ServiceException;
import com.example.finaltask.service.UserService;
import com.example.finaltask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static com.example.finaltask.command.RequestParameterAttributeName.*;
import static com.example.finaltask.command.PagePath.*;
import static com.example.finaltask.entity.UserRole.ADMIN;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        try {
            User user = userService.authenticate(email, password);
            if(user != null){
                request.setAttribute(USER, email);
                page = switch (user.getUserRole()){
                    case ADMIN -> MAIN_PAGE_ADMIN;
                    case CLIENT -> MAIN_PAGE_CLIENT;
                    default -> throw new IllegalStateException("Unexpected value: " + user.getUserRole());
                };
            }else {
                request.setAttribute(LOGIN_MESSAGE, "incorrect login or password");
                page = START_PAGE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
