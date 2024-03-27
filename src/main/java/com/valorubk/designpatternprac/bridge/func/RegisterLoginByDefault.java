package com.valorubk.designpatternprac.bridge.func;

import com.valorubk.designpatternprac.bridge.abst.factory.RegisterLoginComponentFactory;
import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    @Resource
    private UserRepository userRepository;

    @PostConstruct
    private void  initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("Default", this);
    }

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
