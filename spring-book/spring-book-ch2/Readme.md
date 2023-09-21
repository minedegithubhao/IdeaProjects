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
基于Java注解配置spring容器。
包扫描`<context:component-scan base-package="org.example"/>`和注解总是成对出现的，包扫描的意义就在于将扫描路径下所有`@Component`及其派生注解加载到Spring容器当中。
注意[AccountDaoInMemoryImpl.java](configuration-based-annotation%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FAccountDaoInMemoryImpl.java)加载到内存后，Spring为其分配的id是`accountDaoInMemoryImpl`,但是在[AccountServiceImpl.java](configuration-based-annotation%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FAccountServiceImpl.java)中注入的却是`AccountDao`说明`@Autowired`在可根据类型进行注入，并会对类型推断。
