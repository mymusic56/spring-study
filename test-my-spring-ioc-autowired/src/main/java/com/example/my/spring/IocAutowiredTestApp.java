package com.example.my.spring;

import com.example.my.spring.framework.exception.BeansException;
import com.example.my.spring.framework.context.ClassPathXmlApplicationContext;
import com.example.my.spring.service.impl.BServiceImpl;
import com.example.my.spring.service.impl.CServiceImpl;
import com.example.my.spring.service.impl.UserServiceImpl;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

public class IocAutowiredTestApp {
    public static void main(String[] args) throws BeansException {
        String beanConfigPath = "BeanDefinition.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanConfigPath, true);
        UserServiceImpl user = (UserServiceImpl)context.getBean("userService");
        BServiceImpl bService = (BServiceImpl) context.getBean("bService");
        CServiceImpl cService = (CServiceImpl)context.getBean("cService");
        user.sayHello();
        bService.sayHello();
        cService.sayHello();
        System.out.println("-----------------------------");
        user.getBService().sayHello();
        bService.getCService().sayHello();
        cService.getUserService().sayHello();
    }
    public void readeSource(String xmlPath) {
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(xmlPath);
        try {
            Document document = saxReader.read(url);
            Element rootElement = document.getRootElement();
            Iterator elementIterator = rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}