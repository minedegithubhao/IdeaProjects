# 缓存技术
## [simplecachemanager](simplecachemanager)
本示例演示了一个简单的应用程序缓存
1. 声明缓存驱动
```xml
<!--启用@Cacheable、@CacheEvict 、 @CachePut 和 @Caching注解 -->
<!--cache-manager默认查找cacheManager，当然也可以指定Bean-->
<cache:annotation-driven cache-manager="cacheManager"/>
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
            <!--定义一个名叫 users 的缓存区域， 如有需要可以定义多个缓存区域-->
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

    // 注意这里必须使用public修饰
    // 使用AOP的原理实现相同代理类调用被缓存的方法并不会导致缓存拦截
    @Cacheable(value = "users")
    public User getUser(int id) {
        System.out.println("User with id " + id + " requested.");
        return users.get(id);
    }
}
```
## [annotationconfig](annotationconfig)
本示例演示了使用注解配置
>注意缓存抽象使用AOP的原理，相同代理类调用被缓存的方法并不会导致缓存拦截
## [customkey](customkey)
本示例演示类如何自定义缓存的key
```java
public class UserService {

    private Map<Integer, User> users = new HashMap<Integer, User>();
    {
        users.put(1, new User(1, 11122233344L, "Kenan", "5554332088", 37));
        users.put(2, new User(2, 23451323454L, "Mert", "5552345060", 34));
    }

    // 当调用getUser时，会使用nationalId作为key去缓存中查找，而不是Id
    @Cacheable(value = "users", key = "#user.nationalId")
    public User getUser(User user) {
        System.out.println("User with id " + user.getId() + " requested.");
        return users.get(user.getId());
    }
}
```
## [condition](condition)
本示例演示了有条件缓存
```java
public class UserService {

    private Map<Integer, User> users = new HashMap<Integer, User>();
    {
        users.put(1, new User(1, "Kenan", "5554332088", 37));
        users.put(2, new User(2, "Mert", "5552345060", 34));
    }

    // 该方法会将返回值缓存在 users 缓存域中
    // 只缓存age < 35 ，当查询 User 的时候会先从users缓存域中根据 id 去查找如果找到了直接返回User。
    @Cacheable(value = "users", condition = "#user.age < 35")
    public User getUser(User user) {
        System.out.println("User with id " + user.getId() + " requested.");
        return users.get(user.getId());
    }
}
```
## [cacheevict](cacheevict)
该示例演示了从给定的缓存域中去除某一个值
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

    // 从users缓存移除对象
    @CacheEvict("users")
    public void removeUser(int id) {
        users.remove(id);
    }
}
```