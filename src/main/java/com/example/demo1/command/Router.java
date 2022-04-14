package com.example.demo1.command;

import com.example.demo1.constant.PagePath;
import com.example.demo1.constant.RequestParameterAndAttribute;

public class Router {
    private String page = PagePath.START_PAGE;
    private Type type = Type.FORWARD;
    enum Type{
        FORWARD, REDIRECT;
    }

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }
}
