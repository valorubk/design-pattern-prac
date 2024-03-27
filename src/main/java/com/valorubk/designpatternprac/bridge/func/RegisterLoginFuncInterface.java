package com.valorubk.designpatternprac.bridge.func;

import com.valorubk.designpatternprac.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

// 重构,需要把UserService的逻辑移到该类的子类中以完成重构
public interface RegisterLoginFuncInterface {
    String login(String account, String password);
    String register(UserInfo userInfo);
    boolean checkUserExists(String userName);
    String login3rd(HttpServletRequest request);
}
