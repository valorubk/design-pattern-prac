package com.valorubk.designpatternprac.bridge.func;

import com.alibaba.fastjson.JSONObject;
import com.valorubk.designpatternprac.bridge.abst.factory.RegisterLoginComponentFactory;
import com.valorubk.designpatternprac.pojo.UserInfo;
import com.valorubk.designpatternprac.repo.UserRepository;
import com.valorubk.designpatternprac.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@Component
public class RegisterLoginByGitee extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Resource
    private UserRepository userRepository;

    @PostConstruct
    private void  initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("GITEE", this);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
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
        if (super.commonCheckUserExists(userName, userRepository)) {
            return super.commonLogin(userName, password, userRepository);
        }

        // 用户在系统内注册
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(password);
        userInfo.setCreateDate(new Date());
        super.commonRegister(userInfo, userRepository);
        return super.commonLogin(userName, password, userRepository);
    }
}
