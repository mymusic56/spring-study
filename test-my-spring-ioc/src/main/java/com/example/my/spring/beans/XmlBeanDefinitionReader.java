package com.example.my.spring.beans;

import org.dom4j.Element;

import com.example.my.spring.core.Resource;
import com.example.my.spring.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:41
 */
public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource) throws BeansException {
        while (resource.hasNext()) {
            Element next = (Element)resource.next();
            String id = next.attributeValue("id");
            String className = next.attributeValue("name");

            BeanDefinition beanDefinition = new BeanDefinition(id, className);
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
