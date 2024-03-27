package com.valorubk.designpatternprac.service;

import com.valorubk.designpatternprac.bridge.abst.AbstractRegisterLoginComponent;
import com.valorubk.designpatternprac.bridge.abst.RegisterLoginComponent;
import com.valorubk.designpatternprac.bridge.abst.factory.RegisterLoginComponentFactory;
import com.valorubk.designpatternprac.pojo.UserInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 该网站原生的登录系统
 * 该项目对于用户的密码暂时不进行加解密处理
 */
@Service
public class UserBridgeService {

    public String login(String account, String password) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("Default");
        return ((RegisterLoginComponent) component).login(account, password);
    }

    // 用户注册
    public String register(UserInfo userInfo) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("Default");
        return ((RegisterLoginComponent) component).register(userInfo);
    }

    public String login3rd(HttpServletRequest request, String type) {
        AbstractRegisterLoginComponent component = RegisterLoginComponentFactory.getComponent("Default");
        return ((RegisterLoginComponent) component).login3rd(request);
    }
}
