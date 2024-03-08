package org.example.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by mertcaliskan
 * on 18/07/14.
 */
@Component
@Aspect
public class ThisPointcut {

//    @After("bean(*BeanImpl)")
//    @After("@annotation(org.example.markers.MarkerAnnotation)")
    @After("this(org.example.markers.MarkerInterface)")
    public void after() {
        System.out.println("After Advice with pointcut for Marker Interface with this()");
    }
}
