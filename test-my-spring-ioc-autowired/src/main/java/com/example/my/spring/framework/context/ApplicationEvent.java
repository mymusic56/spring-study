package com.example.my.spring.framework.context;

import java.util.EventObject;

/**
 * @author zhangshangji
 * @since 2023/7/4 18:25
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    protected String msg = null;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.msg = source.toString();
    }
}
