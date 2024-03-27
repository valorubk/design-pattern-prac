package com.valorubk.designpatternprac.bridge.func;

import com.valorubk.designpatternprac.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class RegisterLoginByGitee implements RegisterLoginFuncInterface{
    @Override
    public String login(String account, String password) {
        return null;
    }

    @Override
    public String register(UserInfo userInfo) {
        return null;
    }

    @Override
    public boolean checkUserExists(String userName) {
        return false;
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return null;
    }
}
