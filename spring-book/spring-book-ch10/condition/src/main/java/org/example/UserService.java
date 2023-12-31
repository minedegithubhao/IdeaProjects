package org.example;

import org.springframework.cache.annotation.Cacheable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mertcaliskan
 * on 18/08/14.
 */
public class UserService {

    private Map<Integer, User> users = new HashMap<Integer, User>();
    {
        users.put(1, new User(1, "Kenan", "5554332088", 37));
        users.put(2, new User(2, "Mert", "5552345060", 34));
    }

    // 该方法会把返回的 User 进行缓存，当 id 相同的时候将从缓存域中直接读取，condition表明只换从了 age>35的 User
    @Cacheable(value = "users", condition = "#user.age < 35")
    public User getUser(User user) {
        System.out.println("User with id " + user.getId() + " requested.");
        return users.get(user.getId());
    }
}
