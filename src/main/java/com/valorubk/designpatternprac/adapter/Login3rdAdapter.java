package com.valorubk.designpatternprac.adapter;

import com.valorubk.designpatternprac.service.UserService;

public class Login3rdAdapter extends UserService implements Login3rdTarget {
    @Override
    public String loginByGitee(String code, String state) {
        return null;
    }

    @Override
    public String loginByWechat(String... params) {
        return null;
    }

    @Override
    public String loginByQQ(String... params) {
        return null;
    }
}
