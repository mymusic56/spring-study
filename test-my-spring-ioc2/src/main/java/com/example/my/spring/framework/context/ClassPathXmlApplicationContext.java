package com.example.my.spring.framework.context;

import com.example.my.spring.framework.beans.BeanDefinition;
import com.example.my.spring.framework.beans.BeanFactory;
import com.example.my.spring.framework.beans.SimpleBeanFactory;
import com.example.my.spring.framework.beans.XmlBeanDefinitionReader;
import com.example.my.spring.framework.core.ClassPathXmlResource;
import com.example.my.spring.framework.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:52
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private SimpleBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String xmlPath) throws BeansException {
        ClassPathXmlResource xmlResource = new ClassPathXmlResource(xmlPath);

        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanFactory);

        // 注册bean定义
        xmlBeanDefinitionReader.loadBeanDefinition(xmlResource);

        this.beanFactory = simpleBeanFactory;

        // 加载所有的bean
        this.beanFactory.refresh();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.beanFactory.containsBean(beanName);
    }
}
