智能化数字资源管理系统（springMVC + Mybatis）
# 前端控制器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app
        xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
        version="3.0"
        metadata-complete="true">
    <servlet>
        <servlet-name>springMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <!--默认情况下会去WEB-INF下查找[servlet-name]-servlet.xml,由于本文中的servlet-name是springMVC,所以要找的就是springMVC-servlet.xml-->
            <!--通过参数contextConfigLocation，可以配置该配置文件的路径-->
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <!--加载顺序为1,启动时立即加载该servlet -->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!--这里指定了所有的请求都将由名字叫springMVC的Servlet来处理-->
    <servlet-mapping>
        <servlet-name>springMVC</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```
DispatcherServlet会查找`spring-context-mvc.xml`文件作为Spring MVC的上下文，解析该文件内容并创建一个`WebApplicationContext`容器对象，有了该容器就可以很自然的使用IOC、AOP等功能。
# SpringMVC配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 
        <mvc:annotation-driven/>
        启用了Spring MVC项目中的注解功能，如@Controller和@RequestMapping等
        负责注册默认的HandlerMapping和HandlerAdapter，这对于处理HTTP请求很重要。
     -->
    <mvc:annotation-driven/>
    
    <!--
        <context:annotation-config />
        激活那些已经在Spring容器中声明的各种注解，如@Autowired, @Resource, @PostConstruct, @PreDestroy等
    -->
    <context:annotation-config />
    
    <!--
        该注解常与<mvc:annotation-driven/>组合使用
        Spring会自动找到那些用如 @Component, @Service, @Repository, @Controller 等注解标记的类，并将它们作为Beans注册到Spring容器中
    -->
    <context:component-scan base-package="com.smartdrm"/>

    <!-- 视图解析器 -->
    <bean id="viewResolver"
          class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/jsp/"/>
        <property name="suffix" value=".jsp"></property>
    </bean>
</beans>
```
## 如果项目中使用html作为视图，修需要修改配置
```xml
<!-- 视图解析器 -->
<bean id="viewResolver"
      class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/html/"/>
    <property name="suffix" value=".html"></property>
</bean>

<!--
    如果使用html作为view，那么这个配置可以解决404问题
    这个配置会让Spring MVC在找不到合适的Controller处理请求时，将请求转发给Web容器的默认Servlet
-->
<mvc:default-servlet-handler/>
```