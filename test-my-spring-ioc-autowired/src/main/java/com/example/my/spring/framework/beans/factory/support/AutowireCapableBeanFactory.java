package com.example.my.spring.framework.beans.factory.support;

import com.example.my.spring.framework.beans.factory.BeanFactory;
import com.example.my.spring.framework.exception.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理自动注入
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}