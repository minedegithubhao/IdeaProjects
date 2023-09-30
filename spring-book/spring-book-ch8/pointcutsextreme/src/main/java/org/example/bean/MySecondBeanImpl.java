package org.example.bean;

import org.example.markers.MarkerAnnotation;
import org.springframework.stereotype.Component;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
@Component
@MarkerAnnotation
public class MySecondBeanImpl implements MySecondBean {

    @Override
    public void sayHello()  {
        System.out.println("Hello..! Second");
    }
}
