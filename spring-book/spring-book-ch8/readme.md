## [executiontimelogging](executiontimelogging)
该示例演示了如何定义`Before`和`After Returning`
>当同一个类中的一个方法调用另一个方法，后者就不能应用通知

![img.png](img.png)
![img_1.png](img_1.png)
## [executiontimeloggingthrowsadvice](executiontimeloggingthrowsadvice)
该示例演示了如何定义`After Throwing`
```java
public class ExecutionTimeLoggingThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        String className = target.getClass().getCanonicalName();
        String methodName = method.getName();
        // 这里抛出的异常将会覆盖原有的异常by zero
        int i = 1/0;
        System.out.println("Execution of " + className + "#" + methodName + " ended with exception: " + ex.getMessage());
    }
}
```
>如果ThrowsAdvice中抛出异常，则该异常将会覆盖原异常。

## [executiontimeloggingwithafterfinally](executiontimeloggingwithafterfinally)
该示例演示了如何定义`After(Finally)`
## [executiontimeloggingwitharound](executiontimeloggingwitharound)
该示例演示了如何定义`Around`
>注意：只有 `Around` 支持 ProceedingJoinPoint，如果有多个切入点可以通过Ordered接口指定顺序
## [executiontimeloggingaspectj](executiontimeloggingaspectj)
该示例演示了基于XML在Spring中使用`Aspectj`。
## [executiontimeloggingaspectjcglib](executiontimeloggingaspectjcglib)
该示例演示了基于XML在Spring中使用`cglib`, 只需要设置在`<aop:aspectj-autoproxy />` 中加上`proxy-target-class="true"` 属性就有JDK动态代理变成了`Cglib`
