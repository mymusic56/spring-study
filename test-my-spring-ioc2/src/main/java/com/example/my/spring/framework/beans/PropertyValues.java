package com.example.my.spring.framework.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValues(PropertyValue argumentValue) {
        this.propertyValues.add(argumentValue);
    }

    public PropertyValue getIndexedArgument(int index) {
        return propertyValues.get(index);
    }

    public int getPropertyCount() {
        return propertyValues.size();
    }

    public boolean isEmpty() {
        return propertyValues.isEmpty();
    }

}
