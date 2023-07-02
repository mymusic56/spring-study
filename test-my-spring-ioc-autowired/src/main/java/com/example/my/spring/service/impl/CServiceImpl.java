package com.example.my.spring.service.impl;


import com.example.my.spring.framework.beans.factory.annotation.Autowired;
import com.example.my.spring.service.CService;
import com.example.my.spring.service.UserService;

/**
 * @author zhangshengji
 * @since 2023/03/20 21:59
 */
public class CServiceImpl implements CService {

    @Autowired
    private UserService userService;

    private String property1;

    @Override
    public void sayHello() {
        System.out.println("cService hello");
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }
}
