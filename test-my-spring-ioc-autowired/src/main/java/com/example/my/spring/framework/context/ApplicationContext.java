package com.example.my.spring.framework.context;

import com.example.my.spring.framework.beans.factory.config.BeanFactoryPostProcessor;
import com.example.my.spring.framework.beans.factory.config.ConfigurableBeanFactory;
import com.example.my.spring.framework.beans.factory.config.ConfigurableListableBeanFactory;
import com.example.my.spring.framework.beans.factory.config.ListableBeanFactory;
import com.example.my.spring.framework.core.env.Environment;
import com.example.my.spring.framework.core.env.EnvironmentCapable;
import com.example.my.spring.framework.exception.BeansException;

public interface ApplicationContext
    extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher {

    String getApplicationName();

    long getStartupDate();

    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    void refresh() throws BeansException, IllegalStateException;

    void close();

    boolean isActive();
}
