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
## [form-element](form-element)
该示例演示了Springmvc项目如何与表单交互
>表单标签库：标签库的目标是使开发者能够更轻松地创建和渲染表单元素，并将表单数据绑定到后端的 Spring 控制器（Controller）中。位于spring-webmvc项目的META-INF/spring-form.tld
```html
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
```
属性绑定：path指向了一个类的name属性，用户输入的值将会被自动设置到该类的name属性，path只知道属性不知道具体是哪个类
```html
<mvc:label path="name">Name</mvc:label>
```
表单：通过modelAttribute指向了绑定的模型类，path指明了具体绑定的属性
```html
<mvc:form modelAttribute="user" action="result.mvc">
    <mvc:label path="name">Name</mvc:label>
</mvc:form>
```
<context:annotation-config />和<mvc:annotation-driven />的区别
+ <context:annotation-config> 用于启用 Spring 容器中的基本注解处理功能。如 @Autowired、@Resource、@Value 等注解，以便进行依赖注入、属性注入等操作。
+ <mvc:annotation-driven> 用于启用 Spring MVC 中的注解驱动功能。如 @Controller、@RequestMapping、@PathVariable 等注解，以便支持基于注解的 MVC 控制器和请求映射。

总的来说，<context:annotation-config> 用于启用 Spring 容器中的基本注解处理功能，而 <mvc:annotation-driven> 用于启用 Spring MVC 中的注解驱动功能。通常情况下，构建一个 Spring MVC 应用程序，需要包含 <mvc:annotation-driven> 来启用 MVC 注解驱动功能，但不一定需要包含 <context:annotation-config>，因为 <mvc:annotation-driven> 通常已经包括了基本的注解处理功能。
## [bean-validation](bean-validation)
该示例演示了通过注解的方式验，在VO层验证用户输入
## [fileupload](fileupload)
该示例演示了文件上传
## [exceptionhandling](exceptionhandling)
该示例演示了公共异常处理
[GlobalExceptionHandler.java](exceptionhandling%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2Fhandler%2FGlobalExceptionHandler.java)实现了应用程序公共异常处理，通过`@ExceptionHandler`可以定义每一种异常的具体处理方法。
## [internationalisation](internationalisation)
该示例演示了国际化
## [theme](theme)
该示例演示了动态主题