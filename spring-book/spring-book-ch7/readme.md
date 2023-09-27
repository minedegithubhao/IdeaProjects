## [context-configuration-and-caching](context-configuration-and-caching)
该示例演示了如何使用创建集成测试，有如下4中创建方式：
* [AccountIntegrationTests.java](context-configuration-and-caching%2Fsrc%2Ftest%2Fjava%2Forg%2Fexample%2FAccountIntegrationTests.java)基于XML创建。
* [AccountIntegrationTestsWithJavaConfig.java](context-configuration-and-caching%2Fsrc%2Ftest%2Fjava%2Forg%2Fexample%2FAccountIntegrationTestsWithJavaConfig.java)基于Java创建
* [AccountIntegrationTestsWithMixedConfig.java](context-configuration-and-caching%2Fsrc%2Ftest%2Fjava%2Forg%2Fexample%2FAccountIntegrationTestsWithMixedConfig.java)混和创建，既有XML也有Java
* [AccountIntegrationTestsWithInitializer.java](context-configuration-and-caching%2Fsrc%2Ftest%2Fjava%2Forg%2Fexample%2FAccountIntegrationTestsWithInitializer.java)和[TestInitializer.java](context-configuration-and-caching%2Fsrc%2Ftest%2Fjava%2Forg%2Fexample%2FTestInitializer.java)基于ApplicationContextInitializer创建

使用`@ActiveProfiles`指定Profile测试
```java
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
@ActiveProfiles(profiles = {"dev", "c3p0"})
public class IntegrationTests {

} 
```
## [context-config-inheritance](context-config-inheritance)
该示例演示了继承上下文
