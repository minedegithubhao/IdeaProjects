package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mertcaliskan
 * on 18/08/14.
 */
public class Main {

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean(UserService.class);

        User user1 = new User(2, 23451323454L, "Mert", "5552345060", 34);
        User userFetch1 = userService.getUser(user1);
        System.out.println(userFetch1);
        User userFetch2 = userService.getUser(user1);
        System.out.println(userFetch2);

        User user2 = new User(1, 11122233344L, "Kenan", "5554332088", 37);
        User userFetch3 = userService.getUser(user2);
        System.out.println(userFetch3);
        User userFetch4 = userService.getUser(user2);
        System.out.println(userFetch4);

        // 缓存使用nationalId作为key，所以即使id=3不存在，但是只要nationalId=11122233344L存在就可以查找到
        User user3 = new User(3, 11122233344L, "ha", "66", 37);
        User userFetch5 = userService.getUser(user3);
        System.out.println(userFetch5);

    }
}
