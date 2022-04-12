package com.example.demo1.command.impl;

import com.example.demo1.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "pages/register.jsp";
    }
}
