package com.valorubk.designpatternprac.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;

/**
 * 专门负责该项目 HTTP 访问其他服务器的逻辑,基于HttpClient
 * 也可以使用热门的 RestTemplate
 */
public class HttpClientUtils {

    // 发送HTTP请求
    public static JSONObject execute(String url, HttpMethod httpMethod) {
        HttpRequestBase httpRequestBase = null;
        try {
            HttpClient client = HttpClients.createDefault();
            httpRequestBase = httpMethod == HttpMethod.GET ? new HttpGet(url) : new HttpPost(url);
            HttpEntity entity = client.execute(httpRequestBase).getEntity();
            // EntityUtils: Static helpers for dealing with HttpEntitys.
            return JSONObject.parseObject(EntityUtils.toString(entity));
        } catch (IOException e) {
            throw new RuntimeException("HTTP请求失败. url = " + url);
        } finally {
            httpRequestBase.releaseConnection();
        }
    }
}
