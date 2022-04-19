package com.example.finaltask.command.impl.toPageCommand;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.Router;
import com.example.finaltask.command.PagePath;
import com.example.finaltask.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class ToRegisterPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.REGISTER_PAGE);
    }
}
