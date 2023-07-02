package com.example.my.spring.framework.beans.factory.config;

import com.example.my.spring.framework.beans.factory.BeanFactory;

/**
 * 管理Bean依赖关系、Bean处理
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    int getBeanPostProcessorCount();

    void registerDependentBean(String beanName, String dependentBeanName);

    String[] getDependentBeans(String beanName);

    String[] getDependenciesForBean(String beanName);

}
