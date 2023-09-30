package org.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
@Component
@Aspect
public class AtWithinPointcut {

//    @Before("within(org.example.bean.MySecondBeanImpl)")
    @Before("@within(org.example.markers.MarkerAnnotation)")
    public void before() {
        System.out.println("Before Advice with pointcut for Marker Method Annotation.");
    }
}
