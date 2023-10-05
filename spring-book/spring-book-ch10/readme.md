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
       // 仅从缓存中去除，不删除数据
//        users.remove(id);
    }
}
```
## [cacheput](cacheput)
该示例演示了如何向缓存域中添加缓存
```java
public class UserService {

    private Map<Integer, User> users = new HashMap<Integer, User>();
    {
        users.put(1, new User(1, "Kenan"));
        users.put(2, new User(2, "Mert"));
    }

    // 向缓存域中添加数据
    @CachePut(value = "users")
    public User getUser(int id) {
        System.out.println("User with id " + id + " requested.");
        return users.get(id);
    }
}
```
>`@Cacheable`和`@CachePut`的区别在于
> * @Cacheable 主要用于读取缓存，而 @CachePut 主要用于更新缓存
> * `@Cacheable` 从缓存中获取数据，如果缓存中有数据，方法将不会被执行。`@CachePut` 将方法的返回值存入缓存，无论缓存中是否有数据，方法都会被执行。
## [caching](caching)
该示例演示了如何使用组注解
```java
public class ClassroomService {

    private Map<Integer, Person> ppl = new HashMap<Integer, Person>();
    {
        ppl.put(1, new Teacher(1, "Mert"));
        ppl.put(2, new Student(2, "Tugce"));
    }

    // 如果obj的类型是Student则走students缓存域，类型是Teacher走teachers缓存域
    @Caching(cacheable = {
        @Cacheable(value = "students", condition = "#obj instanceof T(org.example.Student)"),
        @Cacheable(value = "teachers", condition = "#obj instanceof T(org.example.Teacher)")
    })
    public Person getPerson(Person obj) {
        return ppl.get(obj.getId());
    }
}
```
## 缓存管理器
### [noopcachemanager](noopcachemanager)(没有用)
该示例演示了noopcachemanager缓存管理器，该管理器实际上并不缓存
### [concurrentmapcachemanager](concurrentmapcachemanager)
该示例演示了`concurrentmapcachemanager`缓存管理器，该管理器配置简单，用法同`SimpleCacheManager`,他们两个的区别在于：
* ConcurrentMapCacheManager 适用于小规模应用程序，它将缓存数据存储在内存中的 ConcurrentHashMap 中。
* SimpleCacheManager 更灵活，可以同时管理多个不同类型的缓存，例如将一个缓存存储在内存中，另一个缓存存储在外部缓存服务器中。
****
### [hazelcastintegration](hazelcastintegration)（****推荐使用该方式进行缓存配置****）
该示例演示了Hazelcast分布式缓存框架
****
>缓存类实现Serializable接口，并有默认的构造器
### [compositecachemanager](compositecachemanager)
该示例演示了在应用程序中使用多种不同的缓存策略
## [initialisecache](initialisecache)
该示例演示了如何在程序启动时，将数据加载到缓存中
```java
@Service
public class UserService {

    private Map<Integer, User> users = new HashMap<>();
    {
        users.put(1, new User(1, "Kenan"));
        users.put(2, new User(2, "Mert"));
    }

    @Autowired
    private CacheManager cacheManager;

    // Spring启动时会调用该方法，将数据加载到 users 缓存域中
    @PostConstruct
    public void setup() {
        Cache usersCache = cacheManager.getCache("users");
        for (Integer key : users.keySet()) {
            usersCache.put(key, users.get(key));
        }
    }

    @Cacheable(value = "users")
    public User getUser(int id) {
        System.out.println("User with id " + id + " requested.");
        return users.get(id);
    }
}
```
## [ehcacheintegration](ehcacheintegration)
该示例演示了如何使用ehcache进行缓存
## [guavaintegration](guavaintegration)
该示例演示了如何使用guava进行缓存
