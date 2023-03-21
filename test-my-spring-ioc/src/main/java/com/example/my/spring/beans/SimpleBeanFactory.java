package com.example.my.spring.beans;

import java.util.HashMap;
import java.util.Map;

import com.example.my.spring.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:45
 */
public class SimpleBeanFactory implements BeanFactory {

    private final Map<String, Object> beans = new HashMap<>();

    @Override
    public Object getBean(String beanName) throws BeansException {

        Object o = beans.get(beanName);
        if (o != null) {
            return o;
        }

        throw new BeansException("未找到bean对象");
    }

    @Override
    public void registerBeanDefinition(BeanDefinition definition) throws BeansException {
        if (beans.containsKey(definition.getId())) {
            return;
        }
        try {
            beans.put(definition.getId(), Class.forName(definition.getName()).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
