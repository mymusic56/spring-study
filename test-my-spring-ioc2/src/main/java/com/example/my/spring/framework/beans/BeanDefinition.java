package com.example.my.spring.framework.beans;

import java.util.List;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:00
 */
public class BeanDefinition {

    private static final String SCOPE_SINGLETON = "singleton";
    private static final String SCOPE_PROTOTYPE = "prototype";

    private String id;

    private String className;

    private String scope = SCOPE_SINGLETON;

    private boolean lazyInit = true;

    private boolean isRef;

    private String[] dependsOn;

    private PropertyValues propertyValues;

    private ArgumentValues argumentValues;

    public BeanDefinition(String id, String name) {
        this.id = id;
        this.className = name;
    }

    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }


    public boolean isRef() {
        return isRef;
    }

    public void setRef(boolean ref) {
        isRef = ref;
    }

    public String[] getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public ArgumentValues getArgumentValues() {
        return argumentValues;
    }

    public void setArgumentValues(ArgumentValues argumentValues) {
        this.argumentValues = argumentValues == null ? new ArgumentValues() : argumentValues;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }
}
