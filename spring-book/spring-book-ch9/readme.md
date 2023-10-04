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
分析器默认使用前缀"#"，后缀为"}",但是也提供了这种修改这种默认方式的方法
```java
@Test
public void helloWorldParsedWithDifferentPrefixAndSuffixOK() {
    // 使用下划线"_"作为前后缀来进行解析
    Expression exp = parser.parseExpression("_'Hello World!'_", new TemplateParserContext("_", "_"));
    String value = exp.getValue(String.class);
    assertThat(value, is("Hello World!"));
}
```
>#{}和${}的区别
* #{}常用于XML配置文件或注解中，例如Spring的bean定义、注解中的@Value注解等。
```xml
<bean id="myBean" class="com.example.MyBean">
    <property name="propertyValue" value="#{someBean.someProperty}" />
</bean>
```
```java
@Value("#{someBean.someProperty}")
private String propertyValue;
```
* ${} 表达式常用于属性文件（例如.properties文件）中
```properties
db.url=jdbc:mysql://${db.host}:${db.port}/mydatabase
```