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
`LocalContainerEntityManagerFactoryBean`是最基本且功能有限的，主要用于测试目的和独立环境
