package com.example.my.spring.framework.context;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);

    void addApplicationListener(ApplicationListener listener);
}
