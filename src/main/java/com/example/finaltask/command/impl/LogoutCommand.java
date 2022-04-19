package com.example.finaltask.command.impl;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.Router;
import com.example.finaltask.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(PagePath.START_PAGE);
    }
}
