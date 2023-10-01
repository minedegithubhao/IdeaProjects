# EL表达式
## [xmlconfig](xmlconfig)
本示例演示类基于XML使用SpEL
```xml
<bean id="show1" class="org.example.MyBean">
    <!--systemProperties是一个保留字，它可以根据一个键值检索系统属性-->
    <property name="message" value="#{systemProperties['user.language']}" />
</bean>
```
## [annotationconfig](annotationconfig)
本示例演示类基于注解使用SpEL
```java
@Component
public class MyBean {

    //systemProperties是一个保留字，它可以根据一个键值检索系统属性
    @Value("#{systemProperties['user.language']}")
    private String message;

    public String getMessage() {
        return message;
    }
}
```
## [helloworld](helloworld)
本示例演示了创建SpEL分析器
```java
// SpelExpressionParser主要用于字符串的解析，解析到已编译好的Expression对象中
private ExpressionParser parser = new SpelExpressionParser();
public void helloWorldParsedOK() {
    // 尝试解析字符串Hello World!
    Expression expression = parser.parseExpression("'Hello World!'");
    // 尝试获取解析后的内容。
    String value = expression.getValue(String.class);
    assertThat(value, is("Hello World!"));
}
```