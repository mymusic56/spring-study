package com.example.my.spring.beans;

import com.example.my.spring.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:32
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
    boolean containsBean(String beanName);
    void registerBeanDefinition(BeanDefinition definition) throws BeansException;
}
