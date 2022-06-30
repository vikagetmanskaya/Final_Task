package com.example.finaltask.command.impl;

import com.example.finaltask.command.Command;
import com.example.finaltask.command.PagePath;
import com.example.finaltask.command.RequestParameterAttributeName;
import com.example.finaltask.command.Router;
import com.example.finaltask.entity.Tattoo;
import com.example.finaltask.exception.CommandException;
import com.example.finaltask.exception.ServiceException;
import com.example.finaltask.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static com.example.finaltask.command.RequestParameterAttributeName.*;
import static com.example.finaltask.command.PagePath.*;

import java.util.List;

public class CatalogCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        TattooServiceImpl tattooService = TattooServiceImpl.getInstance();
        String page;
        try {
            List<Tattoo> allTattoos = tattooService.findAllTattoos();
            request.setAttribute(TATTOOS_LIST, allTattoos);
            page = CATALOG_PAGE;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
