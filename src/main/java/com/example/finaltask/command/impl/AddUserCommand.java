package com.example.finaltask.command.impl;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.Router;
import com.example.finaltask.entity.User;
import com.example.finaltask.exception.CommandException;
import com.example.finaltask.exception.ServiceException;
import com.example.finaltask.service.UserService;
import com.example.finaltask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static com.example.finaltask.command.RequestParameterAttributeName.*;
import static com.example.finaltask.command.PagePath.*;

import java.sql.Date;// make util.Date

public class AddUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page;
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        Date dateOfBirth = Date.valueOf(request.getParameter(DATE_OF_BIRTH));
        UserService userService = UserServiceImpl.getInstance();
        User user = new User.UserBuilder().setEmail(email).setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setDateOfBirth(dateOfBirth)
                .getUser();

        try {
            if(userService.addUser(user)){
                request.setAttribute(REGISTER_MESSAGE, "register successfully");
                page = START_PAGE;
            }else{
                request.setAttribute(REGISTER_MESSAGE, "incorrect data");
                page = REGISTER_PAGE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}