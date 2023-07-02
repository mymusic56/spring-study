package com.example.my.spring.framework.beans.factory.support;

import com.example.my.spring.framework.beans.factory.config.BeanDefinition;
import com.example.my.spring.framework.beans.factory.config.ConfigurableListableBeanFactory;
import com.example.my.spring.framework.exception.BeansException;

import java.util.*;

/**
 * IOC引擎
 */
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory
    implements ConfigurableListableBeanFactory {

    @Override
    public int getBeanPostProcessorCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public void registerDependentBean(String beanName, String dependentBeanName) {

    }

    @Override
    public String[] getDependentBeans(String beanName) {
        return new String[0];
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return new String[0];
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return (String[])this.beanDefinitionNames.toArray();
    }

    /**
     * 根据bean的类型找到bean名称
     * 
     * @param type
     *            Class
     * @return bean名称
     */
    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> beanNames = new ArrayList<>();
        for (String beanName : this.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = this.getBeanDefinition(beanName);

            // 判断当前type class类型是否是beanDefinition对应类，或者父类、父接口
            // 例如传入UserService获取 UserServiceImpl实例的名称
            // 一个接口可能有多个实现类型，所以存在多个bean名称
            if (type.isAssignableFrom(beanDefinition.getClass())) {
                beanNames.add(beanName);
            }
        }
        return (String[])beanNames.toArray();
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        String[] beanNames = getBeanNamesForType(type);

        Map<String, T> beanMap = new LinkedHashMap<>(beanNames.length);
        for (String beanName : beanNames) {
            Object bean = getBean(beanName);
            beanMap.put(beanName, (T)bean);
        }

        return beanMap;
    }
}
