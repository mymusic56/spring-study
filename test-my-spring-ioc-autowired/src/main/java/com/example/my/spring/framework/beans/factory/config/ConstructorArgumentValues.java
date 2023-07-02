package com.example.my.spring.framework.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

public class ConstructorArgumentValues {
    private List<ConstructorArgumentValue> argumentValues = new ArrayList<>();

    public void addArgumentValue(ConstructorArgumentValue argumentValue) {
        this.argumentValues.add(argumentValue);
    }

    public ConstructorArgumentValue getIndexedArgument(int index) {
        return argumentValues.get(index);
    }

    public int getArgumentCount(){
        return argumentValues.size();
    }

    public boolean isEmpty() {
        return argumentValues.isEmpty();
    }
}
