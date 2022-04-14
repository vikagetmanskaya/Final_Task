package com.example.demo1.command.impl;

import com.example.demo1.command.Command;
import com.example.demo1.constant.PagePath;
import com.example.demo1.constant.RequestParameterAndAttribute;
import com.example.demo1.entity.User;
import com.example.demo1.exception.CommandException;
import com.example.demo1.exception.ServiceException;
import com.example.demo1.service.UserService;
import com.example.demo1.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Date;

public class AddUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String email = request.getParameter(RequestParameterAndAttribute.EMAIL);
        String password = request.getParameter(RequestParameterAndAttribute.PASSWORD);
        String firstName = request.getParameter(RequestParameterAndAttribute.FIRST_NAME);
        String lastName = request.getParameter(RequestParameterAndAttribute.LAST_NAME);
        Date dateOfBirth = Date.valueOf(request.getParameter(RequestParameterAndAttribute.DATE_OF_BIRTH));
        UserService userService = UserServiceImpl.getInstance();
        User user = new User(email, password, firstName,lastName,dateOfBirth);

        try {
            if(userService.addUser(user)){
                request.setAttribute(RequestParameterAndAttribute.REGISTER_MESSAGE, "register successfully");
                page = PagePath.START_PAGE;
            }else{
                request.setAttribute(RequestParameterAndAttribute.REGISTER_MESSAGE, "incorrect data");
                page = PagePath.REGISTER_PAGE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}