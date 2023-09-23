# 大纲
## [configuration-based-java](configuration-based-java)
基于Java配置Spring容器案例
## [configuration-based-xml](configuration-based-xml)
基于XML配置Spring容器案例，它和基于Java的区别仅在于元数据的配置一个是基于Java一个是基于XML
>个人理解：之所以使用interface的名称来作为其实现类的id，是因为即使实现类变了，我们也不用区修改代码 
> 
例如：
```java
AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
```
不管AccountService实现类是[AccountDaoInMemoryImpl.java](configuration-based-xml%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FAccountDaoInMemoryImpl.java)，还是[AccountDaoInJDBCImpl.java](configuration-based-xml%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FAccountDaoInJDBCImpl.java)，我们都不需要去修改代码，只需要在xml中去需改元数据即可。
## [configuration-based-annotation](configuration-based-annotation)
基于Java注解配置Spring容器。
包扫描`<context:component-scan base-package="org.example"/>`和注解总是成对出现的，包扫描的意义就在于将扫描路径下所有`@Component`及其派生注解加载到Spring容器当中。
注意[AccountDaoInMemoryImpl.java](configuration-based-annotation%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FAccountDaoInMemoryImpl.java)加载到内存后，Spring为其分配的id是`accountDaoInMemoryImpl`,但是在[AccountServiceImpl.java](configuration-based-annotation%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FAccountServiceImpl.java)中注入的却是`AccountDao`说明`@Autowired`在可根据类型进行注入，并会对类型推断。
## [injection-setter](injection-setter)
setter注入示例，注意：类中要定义setter()方法
## [injection-constructor](injection-constructor)
构造器注入示例，注意：必须要有对应的构造器。
* 一般来说，构造器有多个参数时，参数的放置顺序并不重要，Spring通过参数类型找到合适的构造器。
* 当构造器的参数类型相同，仅仅参数的顺序不同，这时候Spring就无法准确识别出来，需要人工干预，具体参见代码[ch2-beans.xml](injection-constructor%2Fsrc%2Fmain%2Fresources%2Fch2-beans.xml)
* 循环依赖如果两个Bean通过各自的构造函数互为依赖，那么Spring将无法实例化这两个Bean
## [bean-definition-override](bean-definition-override)
该示例演示了重写Bean定义，不同的配置元数据含有两个同名的Bean定义就会发生重写。

`depends-on`如果Bean a直接或间接依赖Bean b，那么可以肯定的是Spring容器首先创建b。但是如果两个Bean定义之间没有直接或间接依赖，那么Bean的创建顺序就由Spring容器内部确定。如果这时候要保证Bean a在Bean b之前创建就可以使用depends-on特性
```xml
<bean id="b" class="org.example.B" depends-on="a"/>
<bean id="a" class="org.example.A"/>
```
## [autowiring-based-xml](autowiring-based-xml)
本示例演示了自动装配。自动装配有三年模式：byType、byName、constructor。
* byType：Spring通过反射的查看Bean中定义的类，然后尝试将容器中可用的Bean注入与其`类型`相匹配的属性
```xml
	<bean id="accountService" class="org.example.AccountServiceImpl" autowire="byType"/>
	<bean id="accountDao" class="org.example.AccountDaoInMemoryImpl"/>
```
当有多个Bean实例适合自动装配到某一个属性时，则需要进行指定，可以利用`aotuwire-candidate`的特性
```xml
	<bean id="accountService" class="org.example.AccountServiceImpl" autowire="byType"/>
	<bean id="accountDao" class="org.example.AccountDaoInMemoryImpl"/>
        <!--aotuwire-candidate=false，告诉Spring容器该Bean取消自动注入的候选资格-->
	<bean id="accountDaoJdbc" class="org.example.AccountDaoJdbcImpl" autowire-candidate="false"/>
```
* byName Spring通过反射的查看Bean中定义的类，然后尝试将容器中可用的Bean注入与其`id`相匹配的属性
## [autowiring-based-java](autowiring-based-java)
本示例演示了基于Java的自动装配，@Autowired注解，优先使用的byType，当适合的装配类型有多个时，则使用byName
虽然`@Qualifier`不太常用，但这里简单说明下@Qualifier用于解决依赖注入时的歧义性问题。当有多个具有相同类型的 Bean 可以注入到一个类中时，@Qualifier 可以与 @Autowired 结合使用，以指定要注入的特定 Bean 的名称或标识符。
## [autowiring-based-annotation](autowiring-based-annotation)
本案例演示基于注解的自动装配，与基于XML和基于Java的一大区别就是，基于注解的配置可以不用编写Setter方法，Spring借助Java反射实现依赖注入。
>当autowired自动注入时，如果类型匹配不到唯一，名字也未匹配到唯一就会报错

如下：Spring通过类型推断匹配到了2个，通过名称`accountDao`也为匹配到，就会报错，这时候的解决办法要么让类型唯一，要么让id唯一
```java
@Autowired
public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
    
@Repository
public class AccountDaoInMemoryImpl implements AccountDao

@Repository
public class AccountDaoJdbcImpl implements AccountDao
```
指定id有以下几种方式：
* 方式一：
```java
@Autowired
public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
    
@Repository("accountDao")
public class AccountDaoInMemoryImpl implements AccountDao

@Repository
public class AccountDaoJdbcImpl implements AccountDao
```
* 方式二：
```java
@Autowired
public void setAccountDao(AccountDao accountDao) {
this.accountDao = accountDao;
}

@Repository
@Qualifier("accountDao")
public class AccountDaoInMemoryImpl implements AccountDao

@Repository
public class AccountDaoJdbcImpl implements AccountDao
```
* 方式三：
```java
@Autowired
@Qualifier("accountDaoInMemoryImpl")
public void setAccountDao(AccountDao accountDao) {
this.accountDao = accountDao;
}

@Repository
public class AccountDaoInMemoryImpl implements AccountDao

@Repository
public class AccountDaoJdbcImpl implements AccountDao
```
总之就是让Spring容器中存在id="accountDao"的Bean，或者告诉Spring注入Bean的id（方式三）
## Bean查找
Spring容器控制之外的其他对想访问Bean，或者Spring管理的没有直接依赖关系Bean之间的访问。常见的方法：

Spring容器控制之外的其他对想访问Bean：
* 将ApplicationContext分配给一个创建完成后可以全局访问的静态变量。
* 对于Web程序来说，Spring提供了一个名为WebApplicationContextUtils的工具类

Spring管理的没有直接依赖关系Bean之间的访问
* 实现ApplicationContextAware接口，Spring创建期间会将自身注入其中
* 对于基于Java和基于注解的配置，可以使用@Autowired将自身注入
## Bean的命名
* 方式一：第一个是名称，其他的都是别名
```xml
<bean name="accountDao,accountDaoInMemoryImpl" class="org.example.AccountDaoInMemoryImpl"/>
```
* 方式二：通过`<alias>`元素
```xml
<bean id="accountDaoInMemoryImpl" class="org.example.AccountDaoInMemoryImpl"/>
<alias name="accountDaoInMemoryImpl" alias="accountDao"/>
```
* 第三种：基于注解配置,这种不是不是取别名，而是修改名称，修改后Spring容器中就不存在名称为accountServiceImpl和accountDaoInMemoryImpl的Bean了
```java
@Service("accountService")
public class AccountServiceImpl implements AccountService {

}
@Repository("accountDao")
public class AccountDaoInMemoryImpl implements AccountDao{
    
}
```
* 第四种：基于Java配置
```java
@Configuration
public class Ch2BeanConfiguration {

    @Bean(name={"accountDao,accountDaoInMemoryImpl"})
    public AccountService accountService() {
        AccountServiceImpl bean = new AccountServiceImpl();
        return bean;
    }
}
```
## [bean-instantiation-methods](bean-instantiation-methods)
本示例演示了基于XML、基于Java、基于注解、Spring自带的实例化Bean的方法
## [bean-scopes](bean-scopes)
本示例演示了Bean的作用域
## [lifecycle-callback-methods](lifecycle-callback-methods)
本示例演示了Bean的生命周期回调方法
* 方式一：基于XMl的回调方法
```xml
<bean id="foo" class="org.example.Foo" init-method="init" destroy-method="destroy"/>
```
* 方式二：基于注解的回调方法
```java
public class Bar {
	@PostConstruct
	public void init() throws Exception {
		System.out.println("init method invoked");
	}
	
	@PreDestroy
	public void destroy() throws RuntimeException {
		System.out.println("destroy method invoked");
	}	
}
```
* 方式三：实现接口
```java
public class Baz implements InitializingBean, DisposableBean {
    
}
```
## [bean-definition-profiles](bean-definition-profiles)
本示例演示了根据profiles选择不同的Bean定义，比如dataSource
## [configuring-environment](configuring-environment)
通过该接口可以管理应用程序所使用的配置文件和属性信息，并刷新spring上下文