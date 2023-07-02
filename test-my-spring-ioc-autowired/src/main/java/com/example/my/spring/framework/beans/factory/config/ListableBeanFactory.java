package com.example.my.spring.framework.beans.factory.config;

import com.example.my.spring.framework.beans.factory.BeanFactory;
import com.example.my.spring.framework.exception.BeansException;

import java.util.Map;

/**
 * 管理beanDefinition集合
 */
public interface ListableBeanFactory extends BeanFactory {
    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    /**
     * 根据class类型获取所有的bean名称
     * 
     * @param type
     * @return
     */
    String[] getBeanNamesForType(Class<?> type);

    /**
     * 根据class类型获取所有beanMap
     * 
     * @param type
     * @return
     * @param <T>
     *            beanMap, key:beanName, value:bean对象
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

}
