package com.valorubk.designpatternprac.bridge.func;

import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    @Resource
    private UserRepository userRepository;

    @Override
    public String login(String account, String password) {
        return super.commonLogin(account, password, userRepository);
    }

    @Override
    public String register(UserInfo userInfo) {
        return super.commonRegister(userInfo, userRepository);
    }

    @Override
    public boolean checkUserExists(String userName) {
        return super.commonCheckUserExists(userName, userRepository);
    }

}
