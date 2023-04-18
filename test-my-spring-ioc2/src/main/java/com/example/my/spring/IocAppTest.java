package com.example.my.spring;

import com.example.my.spring.framework.context.ClassPathXmlApplicationContext;
import com.example.my.spring.framework.exception.BeansException;
import com.example.my.spring.service.BService;
import com.example.my.spring.service.CService;
import com.example.my.spring.service.UserService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * 迭代2，
 * <p>
 * 1. 属性注入
 * 2. 解决循环依赖问题
 *
 * @author zhangshengji
 * @since 2023/03/20 21:55
 */
public class IocAppTest {
    public static void main(String[] args) throws BeansException {
        String beanConfigPath = "BeanDefinition.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanConfigPath);
        UserService user = (UserService) context.getBean("userService");
        BService bService = (BService) context.getBean("bService");
        CService cService = (CService) context.getBean("cService");
        user.sayHello();
        bService.sayHello();
        cService.sayHello();
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
