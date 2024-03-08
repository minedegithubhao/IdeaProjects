package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        applicationContext.registerShutdownHook();
        Foo foo3 = applicationContext.getBean("foo3", Foo.class);
        Foo foo4 = applicationContext.getBean("foo4", Foo.class);

        System.out.println(foo3.getName());
        System.out.println(foo4.getName());
    }
}