package com.example.my.spring.service.impl;

import com.example.my.spring.service.BService;
import com.example.my.spring.service.CService;

/**
 * @author zhangshengji
 * @since 2023/03/20 21:59
 */
public class BServiceImpl implements BService {

    private CService cService;

    @Override
    public void sayHello() {
        System.out.println("BService hello");
    }

    public CService getCService() {
        return cService;
    }

    public void setCService(CService cService) {
        this.cService = cService;
    }
}
