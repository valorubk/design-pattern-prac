package com.valorubk.designpatternprac.bridge.abst;

import com.valorubk.designpatternprac.bridge.func.RegisterLoginFuncInterface;
import com.valorubk.designpatternprac.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class RegisterLoginComponent extends AbstractRegisterLoginComponent {
    public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        super(funcInterface);
    }

    // 功能的实现依赖于使用桥梁调用实现类
    @Override
    public String login(String username, String password) {
        return funcInterface.login(username, password);
    }

    @Override
    protected String register(UserInfo userInfo) {
        return funcInterface.register(userInfo);
    }

    @Override
    protected boolean checkUserExists(String username) {
        return funcInterface.checkUserExists(username);
    }

    @Override
    protected String login3rd(HttpServletRequest request) {
        return funcInterface.login3rd(request);
    }
}
