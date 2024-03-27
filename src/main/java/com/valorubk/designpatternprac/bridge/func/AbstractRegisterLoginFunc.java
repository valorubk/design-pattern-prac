package com.valorubk.designpatternprac.bridge.func;

import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * 以此构建中间的抽象层，减少冗余的代码。
 */
public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    public String commonLogin(String account, String password, UserRepository userRepository) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        // 匹配账号和密码失败就返回错误信息
        if (Objects.isNull(userInfo)) {
            return "account / password ERROR!";
        }
        return "Login success!";
    }

    public String commonRegister(UserInfo userInfo, UserRepository userRepository) {
        // 如果当前账号存在,拒绝注册
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("User already registered.");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Register success!";
    }

    public boolean commonCheckUserExists(String userName, UserRepository userRepository) {
        UserInfo user = userRepository.findByUserName(userName);
        return Objects.nonNull(user);
    }

    @Override
    public String login(String account, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String register(UserInfo userInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean checkUserExists(String userName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }
}
