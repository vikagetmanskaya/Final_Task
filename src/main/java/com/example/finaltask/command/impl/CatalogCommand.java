package com.example.finaltask.command.impl;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.PagePath;
import com.example.finaltask.command.RequestParameterAttributeName;
import com.example.finaltask.command.Router;
import com.example.finaltask.exception.CommandException;
import com.example.finaltask.exception.ServiceException;
import com.example.finaltask.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class CatalogCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TattooServiceImpl tattooService = TattooServiceImpl.getInstance();
        String page;
        try {
            request.setAttribute(RequestParameterAttributeName.TATTOOS_LIST, tattooService.findAllTattoos());
            page = PagePath.CATALOG_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
