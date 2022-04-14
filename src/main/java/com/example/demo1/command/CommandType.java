package com.example.demo1.command;

import com.example.demo1.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    REGISTER(new RegisterCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }


    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
