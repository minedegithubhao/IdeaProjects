package org.example.bean;

import org.example.MarkerAnnotation;
import org.springframework.stereotype.Component;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
@Component
public class MyBeanImpl implements MyBean {

    @Override
    @MarkerAnnotation
    public void sayHello(String name) {
        System.out.println("===3. Actual method execution.");
        System.out.println("Hello..!");
    }
}
