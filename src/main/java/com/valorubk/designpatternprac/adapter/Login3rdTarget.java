package com.valorubk.designpatternprac.adapter;

/**
 * 适配器模式：第三方登录接口
 */
public interface Login3rdTarget {
    String loginByGitee(String code, String state);
    String loginByWechat(String ... params);
    String loginByQQ(String ... params);
}
