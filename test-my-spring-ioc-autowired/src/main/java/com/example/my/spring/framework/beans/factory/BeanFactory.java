package com.example.my.spring.framework.beans.factory;


import com.example.my.spring.framework.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:32
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
    boolean containsBean(String beanName);
}
