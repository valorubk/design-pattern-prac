package com.valorubk.designpatternprac.controller;

import com.valorubk.designpatternprac.adapter.Login3rdAdapter;
import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private Login3rdAdapter login3rdAdapter;

    // 用户登录
    @PostMapping("/login")
    public String login(String account, String password) {
        return userService.login(account, password);
    }

    // 用户注册
    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userService.register(userInfo);
    }

    // 第三方登录-gitee回调接口,供gitee服务器调用
    @GetMapping("/gitee")
    public String gitee(String code, String state) throws IOException {
        return login3rdAdapter.loginByGitee(code, state);
    }


}
