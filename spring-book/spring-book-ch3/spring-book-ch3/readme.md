处理器映射器、处理器适配器、视图解析器的默认实现过程都存储在`DispatcherServlet.properties`
## [basic](basic)
SpringMVC之请求原理示例
```xml
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <!--在SpringMVC项目初始化阶段，要寻找一个配置spring上下文的初始化文件，这个文件约定名字就叫{servlet-name}-servlet.xml,即这里的springmvc-servlet.xml-->
    <servlet-name>springmvc</servlet-name>
    <!--任何以mvc结尾的url都要发送到这里-->
    <url-pattern>*.mvc</url-pattern>
</servlet-mapping>
```
当然它的约定文件名称也是可以修改的，如下：
```xml
<servlet>
    <servlet-name>springmvc</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
    <init-param>
        <!--在这里可以修嘎SpringMVC的约定命名文件-->
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:springmvc-servlet.xml</param-value>
    </init-param>
</servlet>
```
## [basic-annotationconfig](basic-annotationconfig)
SpringMVC之请求原理示例-基于注解的形式
