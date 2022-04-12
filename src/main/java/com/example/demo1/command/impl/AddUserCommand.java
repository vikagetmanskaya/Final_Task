package com.example.demo1.command.impl;

import com.example.demo1.command.Command;
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
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        Date dateOfBirth = Date.valueOf(request.getParameter("date_of_birth"));
        UserService userService = UserServiceImpl.getInstance();
        User user = new User(email, password, firstName,lastName,dateOfBirth);

        try {
            if(userService.addUser(user)){
                request.setAttribute("login_msg", "register successfully");
                page = "index.jsp";
            }else{
                request.setAttribute("login_msg", "incorrect data");
                page = "pages/register.jsp";
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
