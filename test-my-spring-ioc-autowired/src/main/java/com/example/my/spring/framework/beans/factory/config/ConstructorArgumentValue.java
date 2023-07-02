package com.example.my.spring.framework.beans.factory.config;

/**
 * @author zhangshangji
 * @since 2023/4/21 11:48
 */
public class ConstructorArgumentValue {

        private final String type;

        private final String name;

        private final Object value;


        public ConstructorArgumentValue(String type, String name, Object value) {
            this.type = type;
            this.name = name;
            this.value = value;
        }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
