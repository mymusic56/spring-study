package com.example.my.spring.service.impl;

import com.example.my.spring.service.BService;
import com.example.my.spring.service.UserService;

/**
 * @author zhangshengji
 * @since 2023/03/20 21:59
 */
public class UserServiceImpl implements UserService {

    private BService bService;

    private String name;

    private Integer age;

    public UserServiceImpl(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void sayHello() {
        System.out.println("UserService hello");
    }

    public BService getBService() {
        return bService;
    }

    public void setBService(BService bService) {
        this.bService = bService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
