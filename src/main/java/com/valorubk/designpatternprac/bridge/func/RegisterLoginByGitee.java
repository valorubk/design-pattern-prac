package com.valorubk.designpatternprac.bridge.func;

import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class RegisterLoginByGitee implements RegisterLoginFuncInterface {

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Resource
    private UserRepository userRepository;

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
