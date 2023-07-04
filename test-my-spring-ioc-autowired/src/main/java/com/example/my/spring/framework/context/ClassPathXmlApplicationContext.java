package com.example.my.spring.framework.context;

import com.example.my.spring.framework.beans.factory.BeanFactory;
import com.example.my.spring.framework.beans.factory.config.BeanFactoryPostProcessor;
import com.example.my.spring.framework.beans.factory.config.BeanPostProcessor;
import com.example.my.spring.framework.beans.factory.config.ConfigurableListableBeanFactory;
import com.example.my.spring.framework.beans.factory.support.AbstractAutowiredCapableBeanFactory;
import com.example.my.spring.framework.beans.factory.support.AutowiredAnnotationBeanPostProcessor;
import com.example.my.spring.framework.beans.factory.support.DefaultListableBeanFactory;
import com.example.my.spring.framework.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.my.spring.framework.core.ClassPathXmlResource;
import com.example.my.spring.framework.core.env.Environment;
import com.example.my.spring.framework.exception.BeansException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:52
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    public ClassPathXmlApplicationContext(String xmlPath) throws BeansException {
        this(xmlPath, true);
    }

    public ClassPathXmlApplicationContext(String xmlPath, boolean isRefresh) throws BeansException {
        ClassPathXmlResource xmlResource = new ClassPathXmlResource(xmlPath);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        // 注册bean定义
        xmlBeanDefinitionReader.loadBeanDefinition(xmlResource);

        this.beanFactory = beanFactory;

        if (isRefresh) {
            // 加载所有的bean
            this.refresh();
        }
    }

    @Override
    void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {}

    @Override
    void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed..."));

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);

    }

}
