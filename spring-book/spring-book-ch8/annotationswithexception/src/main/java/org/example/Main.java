package org.example;

import org.example.bean.MyOtherBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
public class Main {

    public static void main(String... args) {
        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
            MyOtherBean myOtherBean = context.getBean(MyOtherBean.class);
            myOtherBean.sayHelloWithAnException();
        }
        catch (Exception e) {
            //swallow
        }
    }
}
