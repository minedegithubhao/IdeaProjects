# 缓存技术
## [simplecachemanager](simplecachemanager)
本示例演示了一个简单的应用程序缓存
1. 声明缓存驱动
```xml
<!--启用@Cacheable、@CacheEvict 、 @CachePut 和 @Caching注解 -->
<cache:annotation-driven />
```
2. 将需要缓存的类声明为单例模式
```xml
<bean id="userService" class="org.example.UserService" scope="singleton"/> 
```
3. 定义缓存管理器
```xml
<bean id="cacheManager" class="org.springframework.cache.support.SimpleCacheManager">
    <property name="caches">
        <set>
            <bean id="users" class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean" />
        </set>
    </property>
</bean> 
```
4. 在缓存方法上使用注解
```java
public class UserService {

    private Map<Integer, User> users = new HashMap<Integer, User>();
    {
        users.put(1, new User(1, "Kenan"));
        users.put(2, new User(2, "Mert"));
    }

    @Cacheable(value = "users")
    public User getUser(int id) {
        System.out.println("User with id " + id + " requested.");
        return users.get(id);
    }
}
```