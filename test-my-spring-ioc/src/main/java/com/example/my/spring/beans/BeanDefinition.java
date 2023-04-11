package com.example.my.spring.beans;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:00
 */
public class BeanDefinition {

    private String id;

    private String className;

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
}
