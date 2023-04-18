package com.example.my.spring.framework.beans;

import com.example.my.spring.framework.exception.BeansException;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:45
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanDefinitionRegistry, BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    private List<String> beanNames = new ArrayList<>();

    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    public void refresh() {
        for (String beanName : beanNames) {
            try {
                getBean(beanName);
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取bean的时候才实例化
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        // 获取单利bean
        Object singleton = getSingleton(beanName);
        if (singleton == null) {
            singleton = this.earlySingletonObjects.get(beanName);
            if (singleton == null) {
                BeanDefinition bd = this.getBeanDefinition(beanName);
                singleton = this.createBean(bd);
                this.registerSingleton(beanName, singleton);
            }
        }

        if (singleton == null) {
            throw new BeansException("bean create fail [" + beanName + "]");
        }

        return singleton;
    }

    @Override
    public boolean containsBean(String beanName) {
        return containsSingleton(beanName);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
        this.beanNames.add(beanName);

        // 非懒加载 立即初始化对象
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(beanName);
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.beanNames.remove(beanName);
        this.beanDefinitionMap.remove(beanName);
        this.removeSingleton(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanNames.contains(beanName);
    }

    private Object createBean(BeanDefinition bd) throws BeansException {
        Object bean = this.doCreateBean(bd);

        // 将bean放入缓存
        this.earlySingletonObjects.put(bd.getId(), bean);

        // 依赖注入
        this.handlePropertyValues(bean, bd);

        return bean;
    }

    private void handlePropertyValues(Object bean, BeanDefinition bd) throws BeansException {

        System.out.println("handle property for bean id = " + bd.getId());
        // 反射获取bean的set方法

        if (bd.getPropertyValues() == null || bd.getPropertyValues().isEmpty()) {
            return;
        }

        Class<?> aClass = bean.getClass();
        PropertyValues propertyValues = bd.getPropertyValues();
        for (int i = 0; i < propertyValues.getPropertyCount(); i++) {
            PropertyValue propertyValue = propertyValues.getIndexedArgument(i);
            Object pv;
            String type = propertyValue.getType();
            Class<?>[] parameterTypes = new Class[1];

            if (propertyValue.isRef()) {
                pv = getBean(propertyValue.getName());
                try {
                    parameterTypes[0] = Class.forName(propertyValue.getType());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                pv = propertyValue.getValue();

                if ("Integer".equals(type) || "java.long.Integer".equals(type)) {
                    parameterTypes[0] = Integer.class;
                } else if ("Long".equals(type) || "java.long.Long".equals(type)) {
                    parameterTypes[0] = Long.class;
                } else if ("BigDecimal".equals(type) || "java.long.BigDecimal".equals(type)) {
                    parameterTypes[0] = BigDecimal.class;
                } else {
                    parameterTypes[0] = String.class;
                }
            }
            String methodName =
                "set" + propertyValue.getName().substring(0, 1).toUpperCase() + propertyValue.getName().substring(1);

            Object[] args = new Object[1];
            args[0] = pv;

            try {
                aClass.getMethod(methodName, parameterTypes).invoke(bean, args);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private Object doCreateBean(BeanDefinition bd) {

        Object obj = null;

        try {
            Class<?> aClass = Class.forName(bd.getClassName());

            if (bd.getArgumentValues() != null && !bd.getArgumentValues().isEmpty()) {
                // 有构造器参数
                ArgumentValues argumentValues = bd.getArgumentValues();

                Class<?>[] parameterTypes = new Class[argumentValues.getArgumentCount()];
                Object[] args = new Object[argumentValues.getArgumentCount()];
                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgument(i);

                    if ("String".equals(argumentValue.getType())
                        || "java.lang.String".equals(argumentValue.getType())) {
                        parameterTypes[i] = String.class;
                        args[i] = argumentValue.getValue();
                    } else if ("Integer".equals(argumentValue.getType())
                        || "java.lang.Integer".equals(argumentValue.getType())) {
                        parameterTypes[i] = Integer.class;
                        args[i] = Integer.valueOf((String)argumentValue.getValue());
                    } else if ("int".equals(argumentValue.getType())) {
                        parameterTypes[i] = int.class;
                        args[i] = Integer.valueOf((String)argumentValue.getValue());
                    } else {
                        parameterTypes[i] = String.class;
                        args[i] = argumentValue.getValue();
                    }
                }
                obj = aClass.getConstructor(parameterTypes).newInstance(args);

            } else {
                obj = aClass.newInstance();
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException
            | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        System.out.println(bd.getId() + " bean created. " + bd.getClassName() + " : " + obj.toString());

        return obj;
    }
}
