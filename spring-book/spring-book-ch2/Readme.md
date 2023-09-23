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
* byName