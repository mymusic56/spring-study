package com.example.my.spring.framework.beans;

/**
 * @author zhangshangji
 * @since 2023/4/21 10:53
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     *
     * @param beanName       beanName
     * @param beanDefinition BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取BeanDefinition
     *
     * @param beanName beanName
     */
    void removeBeanDefinition(String beanName);

    /**
     * 获取BeanDefinition
     *
     * @param beanName beanName
     * @return BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 是否包含BeanDefinition
     *
     * @param beanName beanName
     * @return boolean
     */
    boolean containsBeanDefinition(String beanName);
}
