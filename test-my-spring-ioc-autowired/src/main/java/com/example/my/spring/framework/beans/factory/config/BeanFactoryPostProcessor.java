package com.example.my.spring.framework.beans.factory.config;

import com.example.my.spring.framework.beans.factory.BeanFactory;
import com.example.my.spring.framework.exception.BeansException;

public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
