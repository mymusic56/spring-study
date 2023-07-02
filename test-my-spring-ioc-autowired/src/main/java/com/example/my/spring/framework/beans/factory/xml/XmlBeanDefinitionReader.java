package com.example.my.spring.framework.beans.factory.xml;

import com.example.my.spring.framework.beans.factory.config.*;
import com.example.my.spring.framework.core.Resource;
import com.example.my.spring.framework.beans.factory.support.AbstractBeanFactory;
import com.example.my.spring.framework.exception.BeansException;
import com.example.my.spring.framework.beans.factory.config.*;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:41
 */
public class XmlBeanDefinitionReader {
    AbstractBeanFactory beanFactory;

    public XmlBeanDefinitionReader(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinition(Resource resource) throws BeansException {
        while (resource.hasNext()) {
            Element next = (Element)resource.next();
            String beanId = next.attributeValue("id");
            String beanClassName = next.attributeValue("class");

            List<Element> constructorElements = next.elements("constructor-arg");
            List<Element> propertyElements = next.elements("property");

            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanClassName);

            // 遍历constructor-arg
            ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
            constructorElements.forEach(item -> {
                String name = item.attributeValue("name");
                String type = item.attributeValue("type");
                String value = item.attributeValue("value");
                argumentValues.addArgumentValue(new ConstructorArgumentValue(type, name, value));
            });

            // 遍历property
            PropertyValues propertyValues = new PropertyValues();
            List<String> refs = new ArrayList<>();
            propertyElements.forEach(item -> {
                String name = item.attributeValue("name");
                String type = item.attributeValue("type");
                String value = item.attributeValue("value");
                String ref = item.attributeValue("ref");
                boolean isRef = false;
                if (ref != null && !"".equals(ref)) {
                    value = null;
                    isRef = true;
                    refs.add(ref);
                }

                propertyValues.addPropertyValues(new PropertyValue(type, name, value, isRef));
            });

            beanDefinition.setArgumentValues(argumentValues);
            beanDefinition.setPropertyValues(propertyValues);
            beanDefinition.setDependsOn(refs.toArray(new String[0]));

            this.beanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }
}
