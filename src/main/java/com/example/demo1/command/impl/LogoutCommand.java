package com.example.demo1.command.impl;

import com.example.demo1.command.Command;
import com.example.demo1.constant.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PagePath.START_PAGE;
    }
}
