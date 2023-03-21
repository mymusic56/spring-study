package com.example.my.spring.test.service.impl;

import com.example.my.spring.test.service.UserService;

/**
 * @author zhangshengji
 * @since 2023/03/20 21:59
 */
public class UserServiceImpl implements UserService {
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
