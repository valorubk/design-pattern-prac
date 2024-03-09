package com.valorubk.designpatternprac.service;

import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * 该网站原生的登录系统
 * 该项目对于用户的密码暂时不进行加解密处理
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public String login(String account, String password) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        // 匹配账号和密码失败就返回错误信息
        if (Objects.isNull(userInfo)) {
            return "account / password ERROR!";
        }
        return "Login success!";
    }

    // 用户注册
    public String register(UserInfo userInfo) {
        // 如果当前账号存在,拒绝注册
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("User already registered.");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Register success!";
    }

    // 根据用户账号名称检查用户是否已经注册
    public boolean checkUserExists(String userName) {
        UserInfo user = userRepository.findByUserName(userName);
        return Objects.nonNull(user);
    }
}
