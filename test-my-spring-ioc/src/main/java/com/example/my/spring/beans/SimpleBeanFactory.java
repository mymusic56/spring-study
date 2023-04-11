package com.example.my.spring.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.my.spring.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:45
 */
public class SimpleBeanFactory implements BeanFactory {

    private final Map<String, Object> beans = new HashMap<>();

    private List<String> beanNames = new ArrayList<>();

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    /**
     * 获取bean的时候才实例化
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        Object o = beans.get(beanName);
        if (o != null) {
            return o;
        }

        // 实例化
        int i = beanNames.indexOf(beanName);
        if (i == -1) {
            throw new BeansException("bean未注册");
        }

        BeanDefinition definition = beanDefinitions.get(i);
        try {
            o = Class.forName(definition.getClassName()).newInstance();
            beans.put(definition.getId(), o);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean containsBean(String beanName) {
        return beans.containsKey(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition definition) throws BeansException {
        this.beanNames.add(definition.getId());
        this.beanDefinitions.add(definition);
    }
}
