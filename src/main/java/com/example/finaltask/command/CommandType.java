package com.example.finaltask.command;

import com.example.finaltask.command.impl.*;
import com.example.finaltask.command.impl.LogoutCommand;
import com.example.finaltask.command.impl.toPageCommand.ToRegisterPageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    REGISTER(new ToRegisterPageCommand()),
    CATALOG(new CatalogCommand());
    private static final Logger logger = LogManager.getLogger();
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }


    public static Command define(String commandStr) {
        CommandType current;
         try {
             current = CommandType.valueOf(commandStr.toUpperCase());
         }catch (IllegalArgumentException e){
             logger.error("Command " + commandStr + " is missing", e);
             current = DEFAULT;
         }


        return current.command;
    }
}
