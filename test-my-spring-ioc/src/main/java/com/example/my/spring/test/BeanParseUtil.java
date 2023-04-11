package com.example.my.spring.test;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.example.my.spring.beans.BeanDefinition;

/**
 * @author zhangshengji
 * @since 2023/03/20 21:56
 */
public class BeanParseUtil {

    private final Map<String, Object> beans = new HashMap<>();

    private final List<BeanDefinition> beanDefinitions = new ArrayList<>();

    public BeanParseUtil(String beanConfigPath) {
        this.readXml(beanConfigPath);
        this.initInstance();
    }

    private void readXml(String xmlPath) {
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(xmlPath);
        try {
            Document document = saxReader.read(url);
            Element rootElement = document.getRootElement();

            for (Element element : (List<Element>)rootElement.elements()) {
                String id = element.attributeValue("id");
                String name = element.attributeValue("name");

                BeanDefinition beanDefinition = new BeanDefinition(id, name);
                beanDefinitions.add(beanDefinition);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void initInstance() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            String name = beanDefinition.getClassName();
            try {
                beans.put(beanDefinition.getId(), Class.forName(name).newInstance());
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getBean(String beanName) {
        return beans.get(beanName);
    }
}
