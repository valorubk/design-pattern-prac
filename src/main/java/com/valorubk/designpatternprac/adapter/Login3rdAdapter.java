package com.valorubk.designpatternprac.adapter;

import com.alibaba.fastjson.JSONObject;
import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.service.UserService;
import com.valorubk.designpatternprac.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 网站适配第三方的登录系统,使用继承方式进行适配
 * 适配器模式: 适配者
 * 其实可以将注册和登录功能移到这个类里,完全移除Controller对UserService的依赖,看项目的实际情况
 */
@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget {

    @Value("${gitee.state}")
    private String giteeState;

    @Value("${gitee.token.url}")
    private String giteeTokenUrl;

    @Value("${gitee.user.url}")
    private String giteeUserUrl;

    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    // 通过 Gitee 登录
    @Override
    public String loginByGitee(String code, String state) {
        // 1. 验证state是否一致
        if (!Objects.equals(state, giteeState)) {
            throw new UnsupportedOperationException("Invalid state!!!");
        }

        // 2. 携带平台的code, 请求Gitee平台获取token
        String tokenRequestUrl = giteeTokenUrl + code;
        JSONObject tokenResponse = HttpClientUtils.execute(tokenRequestUrl, HttpMethod.POST);
        String accessToken = String.valueOf(tokenResponse.get("access_token")); // 这个字符串化的方法防止了Object==null的空指针问题

        // 3. 携带token, 请求用户信息
        String userRequestUrl = giteeUserUrl + accessToken;
        JSONObject userInfoResponse = HttpClientUtils.execute(userRequestUrl, HttpMethod.GET);
        String giteeUserName = String.valueOf(userInfoResponse.get("name"));

        // 4. 带上后缀去请求本系统内的用户信息
        String userName = giteeUserPrefix + giteeUserName;
        String password = userName; // 这个就随便定了, 默认就是用户的用户名

        return autoRegister3rdAndLogin(userName, password);
    }

    /**
     * 验证第三方账号在本系统内是否注册,如未注册,则自动注册后再登录
     * 复用已有代码
     */
    private String autoRegister3rdAndLogin(String userName, String password) {
        // 如果已注册过,则直接登录
        if (super.checkUserExists(userName)) {
            return super.login(userName, password);
        }

        // 用户在系统内注册
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(password);
        userInfo.setCreateDate(new Date());
        super.register(userInfo);
        return super.login(userName, password);
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
