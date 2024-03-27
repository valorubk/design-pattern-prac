package com.valorubk.designpatternprac.controller;

import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.service.UserBridgeService;
import com.valorubk.designpatternprac.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @GetMapping("/gitee")
    public String gitee(HttpServletRequest request) throws IOException {
        return userBridgeService.login3rd(request, "GITEE");
    }
}
