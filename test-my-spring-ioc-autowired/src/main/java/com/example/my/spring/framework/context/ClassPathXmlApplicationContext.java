package com.example.my.spring.framework.context;


import com.example.my.spring.framework.beans.factory.BeanFactory;
import com.example.my.spring.framework.beans.factory.support.AbstractAutowiredCapableBeanFactory;
import com.example.my.spring.framework.beans.factory.support.AutowiredAnnotationBeanPostProcessor;
import com.example.my.spring.framework.beans.factory.support.DefaultListableBeanFactory;
import com.example.my.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.my.spring.framework.core.ClassPathXmlResource;
import com.example.my.spring.framework.exception.BeansException;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:52
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private AbstractAutowiredCapableBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String xmlPath, boolean isRefresh) throws BeansException {
        ClassPathXmlResource xmlResource = new ClassPathXmlResource(xmlPath);

        AbstractAutowiredCapableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        // 注册bean定义
        xmlBeanDefinitionReader.loadBeanDefinition(xmlResource);

        this.beanFactory = beanFactory;

        if (isRefresh) {
            // 加载所有的bean
            this.refresh();
        }
    }

    private void refresh() {
        // 注册beanPostProcessor
        registerBeanPostProcessor(this.beanFactory);
        this.beanFactory.refresh();
    }

    private void registerBeanPostProcessor(AbstractAutowiredCapableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
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
    public boolean isSingleton(String name) {
        return beanFactory.isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) {
        return beanFactory.isPrototype(name);
    }

    @Override
    public Class<?> getType(String name) {
        return beanFactory.getType(name);
    }
}
