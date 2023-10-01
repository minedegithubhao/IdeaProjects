package org.example;

import org.example.bean.Horse;
import org.example.bean.IBird;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
public class Main {

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml", Main.class);
        Object pegasus = context.getBean("pegasus");

        ((Horse) pegasus).ride();
        ((IBird) pegasus).fly();
    }
}