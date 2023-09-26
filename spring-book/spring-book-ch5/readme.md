## [configuring-jpa](configuring-jpa)
演示了如何配置JPA（Hibernate）
`META-INF/persistence.xml`是JPA的配置的主要入口
## [configuring-and-using-jdbctemplate](configuring-and-using-jdbctemplate)
演示了如何使用JPA进行简单的新增。
```java
@OneToMany(cascade=CascadeType.ALL)
@JoinColumn(name="student_id")
private Set<Book> books = new HashSet<Book>();
```
`@OneToMany(cascade=CascadeType.ALL)`表示了[Student.java](configuring-and-using-jdbctemplate%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FStudent.java)和[Book.java](configuring-and-using-jdbctemplate%2Fsrc%2Fmain%2Fjava%2Forg%2Fexample%2FBook.java)是一对多的关系。cascade=CascadeType.ALL在ORM世界也叫传播持久化，这样做的好处就是不必显式的持久化与Student关联的每个Book，即在本案例中保存学生的时候也同时保存了学生所有Book。
`@JoinColumn(name="student_id")`表示外键是`Book`表中的`student_id`
```java
Book book2 = entityManager.getReference(Book.class, 2L);
```
不确定要检索的实体是否存在，使用 entityManager.find() 可以安全地返回 null，而 entityManager.getReference() 会引发异常。
* entityManager.getReference() 时，通常是为了提高性能，因为它不会立即检索整个实体对象，而只是获取一个代理引用
* sentityManager.find() 时，您需要立即获取完整的实体对象的数据，这在您需要查找并操作整个实体时很有用。
## [querying-database-with-jpaql](querying-database-with-jpaql)
本示例介绍了JPA QL 查询数据库
## [configuring-and-using-jpa-with-spring](configuring-and-using-jpa-with-spring)
基于 Spring配置和使用 JPA
`LocalContainerEntityManagerFactoryBean`是最灵活且最强大的JPA配置方法，主要用于测试目的和独立环境
```java
@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    // 针对不同的服务商创建一个实际的 EntityManagerFactory
    factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    factoryBean.setDataSource(dataSource());
    factoryBean.setPackagesToScan("org.example");
    factoryBean.setJpaPropertyMap(jpaProperties());
    return factoryBean;
}
```
## [using-persistence-unit](using-persistence-unit)
使用`@PersistenceUnit`可以将Spring容器中定义的EntityManagerFactory注入到Dao Bean中
## [using-persistence-context](using-persistence-context)
使用`@PersistenceContext`可以将Spring容器中定义的EntityManager注入到Dao Bean中。

`@EnableTransactionManagement`启用Spring管理事务
```java
@Bean
@Autowired //这里@Autowired是是告诉Spring将找到一个 EntityManagerFactory bean 注入到 行参中。
public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
```
## [handling-translating-exceptions](handling-translating-exceptions)
使用Spring的异常管理和转化功能：
* 使用`@Repository`注解标记Bean。
* 需要从`PersistenceExceptionTranslationPostProcessor`类创建一个Bean定义
```java
@Bean
public static PersistenceExceptionTranslationPostProcessor 
    persistenceExceptionTranslationPostProcessor() {
    PersistenceExceptionTranslationPostProcessor bean = 
    new PersistenceExceptionTranslationPostProcessor();
    return bean;
}
```
