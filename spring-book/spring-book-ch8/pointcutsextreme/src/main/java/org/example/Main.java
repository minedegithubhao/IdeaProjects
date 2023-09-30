package org.example;


import org.example.bean.MyBean;
import org.example.bean.MySecondBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
public class Main {

    public static void main(String... args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        MyBean myBean = context.getBean(MyBean.class);
        myBean.sayHello();

        MySecondBean mySecondBean = context.getBean(MySecondBean.class);
        mySecondBean.sayHello();
    }
}
