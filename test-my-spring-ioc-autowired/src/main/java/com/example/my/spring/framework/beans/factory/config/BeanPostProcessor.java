package com.example.my.spring.framework.beans.factory.config;

import com.example.my.spring.framework.exception.BeansException;
import com.example.my.spring.framework.beans.factory.support.AutowireCapableBeanFactory;

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    void setBeanFactory(AutowireCapableBeanFactory beanFactory);

}
