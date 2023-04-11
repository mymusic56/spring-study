package com.example.my.spring.context;

import com.example.my.spring.beans.BeanDefinition;
import com.example.my.spring.beans.BeanFactory;
import com.example.my.spring.beans.SimpleBeanFactory;
import com.example.my.spring.beans.XmlBeanDefinitionReader;
import com.example.my.spring.core.ClassPathXmlResource;
import com.example.my.spring.exception.BeansException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:52
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String xmlPath) throws BeansException {
        ClassPathXmlResource xmlResource = new ClassPathXmlResource(xmlPath);

        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanFactory);

        xmlBeanDefinitionReader.loadBeanDefinition(xmlResource);

        this.beanFactory = simpleBeanFactory;
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.beanFactory.containsBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition definition) throws BeansException {
        this.beanFactory.registerBeanDefinition(definition);
    }
}
