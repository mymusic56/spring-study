package com.example.my.spring.test;

import com.example.my.spring.test.service.UserService;

/**
 * 初始版本
 *
 * @author zhangshengji
 * @since 2023/03/20 21:55
 */
public class BeanTest {
    public static void main(String[] args) {
        String beanConfigPath = "BeanDefinition.xml";
        BeanParseUtil beanParseUtil = new BeanParseUtil(beanConfigPath);
        UserService user = (UserService)beanParseUtil.getBean("userService");
        user.sayHello();
    }
}
