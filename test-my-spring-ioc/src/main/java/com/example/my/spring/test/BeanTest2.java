package com.example.my.spring.test;

import com.example.my.spring.context.ClassPathXmlApplicationContext;
import com.example.my.spring.exception.BeansException;
import com.example.my.spring.test.service.UserService;

/**
 *
 * 迭代1，对BeanParseUtil进行分解
 *
 * @author zhangshengji
 * @since 2023/03/20 21:55
 */
public class BeanTest2 {
    public static void main(String[] args) throws BeansException {
        String beanConfigPath = "BeanDefinition.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanConfigPath);
        UserService user = (UserService)context.getBean("userService");
        user.sayHello();
    }
}
