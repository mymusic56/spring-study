package com.example.my.spring.framework.beans.factory.support;

import com.example.my.spring.framework.exception.BeansException;

import java.util.ArrayList;
import java.util.List;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    private List<AutowiredAnnotationBeanPostProcessor> processorList = new ArrayList<>();

    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor processor) {
        this.processorList.add(processor);
    }

    public int getBeanPostProcessorCount() {
        return this.processorList.size();
    }

    public List<AutowiredAnnotationBeanPostProcessor> getBeanPostProcessors() {
        return this.processorList;
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName)
        throws BeansException {
        // 使用AutowiredAnnotationBeanPostProcessor 处理bean上的注解
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor processor : processorList) {
            processor.setBeanFactory(this);
            result = processor.postProcessBeforeInitialization(existingBean, beanName);
            if (result == null) {
                return null;
            }
        }

        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName)
        throws BeansException {
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor processor : processorList) {
            processor.setBeanFactory(this);
            result = processor.postProcessAfterInitialization(existingBean, beanName);
            if (result == null) {
                return null;
            }
        }
        return result;
    }
}