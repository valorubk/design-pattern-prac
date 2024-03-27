package com.valorubk.designpatternprac.service;

import com.valorubk.designpatternprac.bridge.abst.AbstractRegisterLoginComponent;
import com.valorubk.designpatternprac.bridge.abst.RegisterLoginComponent;
import com.valorubk.designpatternprac.bridge.func.RegisterLoginByDefault;
import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * 该网站原生的登录系统
 * 该项目对于用户的密码暂时不进行加解密处理
 */
@Service
public class UserBridgeService {

    public String login(String account, String password) {
        AbstractRegisterLoginComponent registerLoginComponent = new RegisterLoginComponent(new RegisterLoginByDefault());
        return ((RegisterLoginComponent) registerLoginComponent).login(account, password);
    }

    // 用户注册
    public String register(UserInfo userInfo) {
        return null;
    }

    public String login3rd(HttpServletRequest request, String type) {
        return null;
    }
}
