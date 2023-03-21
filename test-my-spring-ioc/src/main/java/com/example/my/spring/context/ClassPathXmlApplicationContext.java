package com.example.my.spring.context;

import com.example.my.spring.beans.BeanDefinition;
import com.example.my.spring.beans.BeanFactory;
import com.example.my.spring.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:52
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    public ClassPathXmlApplicationContext(String xmlPath) {

    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return null;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition definition) throws BeansException {

    }
}
