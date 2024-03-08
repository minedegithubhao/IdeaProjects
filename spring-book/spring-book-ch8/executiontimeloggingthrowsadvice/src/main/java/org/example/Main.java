package org.example;

import org.example.bean.MyBean;
import org.example.bean.MyOtherBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: mertcaliskan
 * Date: 25/06/14
 */
public class Main {

    public static void main(String... args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml", Main.class);
        MyBean myBean = context.getBean(MyBean.class);
        myBean.sayHello();

        try {
            MyOtherBean myOtherBean = context.getBean(MyOtherBean.class);
            myOtherBean.sayHelloWithAnException();
        }
        catch (Exception e) {
            //swallow
            System.out.println("catch Exception...");
        }
    }
}
