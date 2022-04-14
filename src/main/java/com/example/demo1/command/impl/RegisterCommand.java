package com.example.demo1.command.impl;

import com.example.demo1.command.Command;
import com.example.demo1.constant.PagePath;
import com.example.demo1.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return PagePath.REGISTER_PAGE;
    }
}
