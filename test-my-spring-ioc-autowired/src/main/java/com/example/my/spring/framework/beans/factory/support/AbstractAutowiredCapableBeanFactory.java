package com.example.my.spring.framework.beans.factory.support;

import com.example.my.spring.framework.beans.factory.config.BeanPostProcessor;
import com.example.my.spring.framework.exception.BeansException;

import java.util.ArrayList;
import java.util.List;

public class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 防止重复
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public int getBeanProcessorCount() {
        return this.beanPostProcessorList.size();
    }

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return this.getBeanPostProcessorList();
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
        throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
            beanPostProcessor.setBeanFactory(this);
            result = beanPostProcessor.postProcessBeforeInitialization(existingBean, beanName);
            if (result == null) {
                return null;
            }
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
        throws BeansException {
        Object result = existingBean;

        for (BeanPostProcessor beanPostProcessor : this.beanPostProcessorList) {
            result = beanPostProcessor.postProcessAfterInitialization(beanPostProcessor, beanName);
            if (result == null) {
                return null;
            }
        }

        return result;
    }
}
