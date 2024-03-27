package com.valorubk.designpatternprac.controller;

import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.service.UserBridgeService;
import com.valorubk.designpatternprac.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bridge")
public class UserBridgeController {

    @Resource
    private UserBridgeService userBridgeService;

    @PostMapping("/login")
    public String login(String account, String password) {
        return userBridgeService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userBridgeService.register(userInfo);
    }
}
