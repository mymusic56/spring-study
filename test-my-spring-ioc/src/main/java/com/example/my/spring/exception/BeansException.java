package com.example.my.spring.exception;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:30
 */
public class BeansException extends Exception {

    private static final long serialVersionUID = -5972023687409166567L;

    public BeansException(String message) {
        super(message);
    }
}
