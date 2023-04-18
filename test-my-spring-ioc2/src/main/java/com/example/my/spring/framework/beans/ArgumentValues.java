package com.example.my.spring.framework.beans;

import java.util.ArrayList;
import java.util.List;

public class ArgumentValues {
    private List<ArgumentValue> argumentValues = new ArrayList<>();

    public void addArgumentValue(ArgumentValue argumentValue) {
        this.argumentValues.add(argumentValue);
    }

    public ArgumentValue getIndexedArgument(int index) {
        return argumentValues.get(index);
    }

    public int getArgumentCount(){
        return argumentValues.size();
    }

    public boolean isEmpty() {
        return argumentValues.isEmpty();
    }
}
