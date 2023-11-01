智能化数字资源管理系统（springMVC + Mybatis）
# 前端控制器
```xml
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <!--默认情况下会去WEB-INF下查找[servlet-name]-servlet.xml,由于本文中的servlet-name是springMVC,所以要找的就是springMVC-servlet.xml-->
        <!--通过参数contextConfigLocation，可以配置该配置文件的路径-->
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-context-mvc.xml</param-value>
    </init-param>
    <!--加载顺序为1,启动时立即加载该servlet -->
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```
DispatcherServlet会查找`spring-context-mvc.xml`文件作为Spring MVC的上下文，解析该文件内容并创建一个`WebApplicationContext`容器对象，有了该容器就可以很自然的使用IOC、AOP等功能。
