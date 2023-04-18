package com.example.my.spring.framework.beans;

public interface SingletonBeanRegistry {
    
    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

}
