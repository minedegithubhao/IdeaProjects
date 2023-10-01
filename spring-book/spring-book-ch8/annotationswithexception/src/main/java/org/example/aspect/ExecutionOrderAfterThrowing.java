package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
@Component
@Aspect
public class ExecutionOrderAfterThrowing {

    @AfterThrowing(value = "within(org.example.bean.MyOtherBeanImpl)", throwing = "t")
    public void afterThrowing(JoinPoint joinPoint, Throwable t) {
        System.out.println("===3 After throwing an exception: " + t.getMessage());
    }
}
