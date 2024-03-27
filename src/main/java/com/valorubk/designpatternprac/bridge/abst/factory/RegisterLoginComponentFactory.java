package com.valorubk.designpatternprac.bridge.abst.factory;

import com.valorubk.designpatternprac.bridge.abst.AbstractRegisterLoginComponent;
import com.valorubk.designpatternprac.bridge.abst.RegisterLoginComponent;
import com.valorubk.designpatternprac.bridge.func.RegisterLoginFuncInterface;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterLoginComponentFactory {
    public static Map<String, AbstractRegisterLoginComponent> componentMap = new ConcurrentHashMap<>();
    public static Map<String, RegisterLoginFuncInterface> funcMap = new ConcurrentHashMap<>();

    // 根据不同的登录类型，获取不同的AbsComponent
    public static AbstractRegisterLoginComponent getComponent(String type) {
        AbstractRegisterLoginComponent component = componentMap.get(type);
        if (Objects.isNull(component)) {
            // 并发情况下使用双重检查锁
            synchronized (componentMap) {
                component = componentMap.get(type);
                if (Objects.isNull(component)) {
                    componentMap.put(type, new RegisterLoginComponent(funcMap.get(type)));
                }
            }
        }
        return component;
    }
}
