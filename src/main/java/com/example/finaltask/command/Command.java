package com.example.finaltask.command;

import com.example.finaltask.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
    default void refresh(){}
}
