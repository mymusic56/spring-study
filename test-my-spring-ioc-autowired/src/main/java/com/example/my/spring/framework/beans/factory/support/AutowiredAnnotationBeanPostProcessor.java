package com.example.my.spring.framework.beans.factory.support;

import com.example.my.spring.framework.beans.factory.annotation.Autowired;
import com.example.my.spring.framework.exception.BeansException;
import com.example.my.spring.framework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private AutowireCapableBeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 完成使用注解注入属性
        Class<?> aClass = bean.getClass();

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            boolean annotationPresent = field.isAnnotationPresent(Autowired.class);
            if (annotationPresent) {
                // todo 初始版本通过属性名称 当beanName
                Object autowiredBean = this.beanFactory.getBean(field.getName());

                field.setAccessible(true);
                try {
                    field.set(bean, autowiredBean);
                    System.out.println("autowire " + field.getName() + " for bean " + beanName);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
