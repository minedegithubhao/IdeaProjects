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
## [context-caching](context-caching)
该示例演示了多个测试类指定完全相同的XML位置和配置类，那么将之创建一次ApplicationContext实例。
## [dependency-injection-in-tests](dependency-injection-in-tests)
该示例演示了集成测试，自动注入
## [transaction-management](transaction-management)
该示例演示了继承测试时，使用事务。

为了确保测试方法对数据库的影响不会对数据库状态产生永久性的改变。这样可以保证测试的独立性，每次测试都从一个干净的状态开始。 默认情况下，Spring 测试框架会在测试方法执行结束后回滚事务。`@Rollback(false)` 来告诉测试框架不要回滚事务，使得测试方法执行后对数据库的更改保留在数据库中。

`@Transactional`注解默认会在容器中寻找名称为`transactionManager`的Bean，并在测试完成后执行回滚。然而这种行为可以通过注解`@TransactionConfiguration`更改，如下：
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
// Spring容器回去查找名称为myTxMgr的Bean来进行事务管理，并设置测试完成后提交事务(因为 defaultRollback设置成了false)
@TransactionConfiguration(transactionManager = "myTxMgr", defaultRollback = false)
public class TransactionalTests {
    
}
```
## [loading-webapplicationcontext](loading-webapplicationcontext)
测试Web应用程序
## [testing-request-and-session-scoped-beans](testing-request-and-session-scoped-beans)
测试Request和Session域中的Bean
## [test-mvc](test-mvc)
演示了使用mvc的集成测试案例