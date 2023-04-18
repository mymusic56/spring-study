package com.example.my.spring.framework.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * @author zhangshengji
 * @since 2023/03/20 22:36
 */
public class ClassPathXmlResource implements Resource {

    Document document;
    Element rootElement;

    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String xmlPath) {
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(xmlPath);
        try {
            document = saxReader.read(url);
            rootElement = document.getRootElement();
            elementIterator = rootElement.elementIterator();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return elementIterator.next();
    }
}
