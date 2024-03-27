package com.valorubk.designpatternprac.bridge.func;

import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

public class RegisterLoginByDefault implements RegisterLoginFuncInterface {

    @Resource
    private UserRepository userRepository;

    @Override
    public String login(String account, String password) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        // 匹配账号和密码失败就返回错误信息
        if (Objects.isNull(userInfo)) {
            return "account / password ERROR!";
        }
        return "Login success!";
    }

    @Override
    public String register(UserInfo userInfo) {
        // 如果当前账号存在,拒绝注册
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("User already registered.");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Register success!";
    }

    @Override
    public boolean checkUserExists(String userName) {
        UserInfo user = userRepository.findByUserName(userName);
        return Objects.nonNull(user);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return null;
    }
}
