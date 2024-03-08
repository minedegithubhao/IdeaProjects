package org.example.bean;

import org.example.markers.MarkerAnnotation;
import org.example.markers.MarkerInterface;
import org.springframework.stereotype.Component;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
@Component
public class MyBeanImpl implements MyBean, MarkerInterface {

    @Override
//    @MarkerAnnotation
    public void sayHello() {
        System.out.println("Hello..!");
    }
}