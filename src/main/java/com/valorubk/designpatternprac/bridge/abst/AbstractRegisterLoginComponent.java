package com.valorubk.designpatternprac.bridge.abst;

import com.valorubk.designpatternprac.bridge.func.RegisterLoginFuncInterface;
import com.valorubk.designpatternprac.pojo.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Client端，提供用户的调用入口
 */
public abstract class AbstractRegisterLoginComponent {
    // 桥：连接抽象类和Implementor
    protected RegisterLoginFuncInterface funcInterface;

    public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        validate(funcInterface);
        this.funcInterface = funcInterface;
    }

    protected final void validate(RegisterLoginFuncInterface funcInterface) {
        if (!(funcInterface instanceof RegisterLoginFuncInterface)) {
            throw new UnsupportedOperationException("Unknown register/login func type!!!");
        }
    }

    protected abstract String login(String username, String password);
    protected abstract String register(UserInfo userInfo);
    protected abstract boolean checkUserExists(String username);
    protected abstract String login3rd(HttpServletRequest request);
}
