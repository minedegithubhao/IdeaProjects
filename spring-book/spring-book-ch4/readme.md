## [configuring-datasource](configuring-datasource)
该示例演示了Spinrgmvc连接H2数据库
## [embedded-db-support](embedded-db-support)
该示例演示了Spinrgmvc对嵌入式数据库的支持数据库
## [configuring-datasource-pool](configuring-datasource-pool)[pooled-datasource](pooled-datasource)
该示例演示了H2数据库连接池
## [configuring-and-using-jdbctemplate](configuring-and-using-jdbctemplate)
该示例演示了如何配置JdbcTemplate
## [running-queries-with-jdbctemplate](running-queries-with-jdbctemplate)
该示例演示了如何使用JdbcTemplate进行查询,返回T
```java
public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args){}
```
## [using-named-parameters](using-named-parameters)
该示例演示了如何使用JdbcTemplate使用参数查询,返回List<T>
```java
public <T> List<T> query(String sql, Map<String, ?> paramMap, RowMapper<T> rowMapper) throws DataAccessException {}
```
## [queries-with-in-clause](queries-with-in-clause)
该示例演示了如何使用JdbcTemplate使用in查询,返回List<T>
```java
public <T> List<T> query(String sql, SqlParameterSource paramSource, RowMapper<T> rowMapper) throws DataAccessException {}
```
注意：in的支持的List大小有限制，在使用的时候需要格外注意
## [using-preparedstatements](using-preparedstatements)
该示例演示了如何使用PreparedStatement进行数据库查询,可以防止SQL注入，还能提高执行效率(预处理只会执行一次)
```java
public <T> List<T> query(PreparedStatementCreator psc, RowMapper<T> rowMapper) throws DataAccessException {}
```
## [inserting-updating-deleting-records](inserting-updating-deleting-records)
该示例演示了如何使用JdbcTemplate进行插入、更新、删除。

插入
```java
public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
```
更新
```java
public int update(String sql, Object... args){}
```
删除
```java
public int update(String sql, Object... args){}
```
## [calling-storedprocedures](calling-storedprocedures)
该示例演示了如何使用JdbcTemplate调用存储过程、存储方法。
## [performing-batch-operations](performing-batch-operations)
该示例演示了如何使用JdbcTemplate执行批处理
## [handling-lob-objects](handling-lob-objects)
该示例演示了如何使用JdbcTemplate处理大型对象LOB(Large Object),如二进制大型数据CLOB(Character Large Object)、大型文本数据BLOB(Binary Large Object)
## [encapsulating-sql-queries](encapsulating-sql-queries)
该示例演示了如何使用spring将SQL封装为Java对象
使用MappingSqlQuery封装
```java
	public Account find(long accountId) {
		return accountByIdQuery.findObject(accountId);
	}
```
## [encapsulating-dml-operations](encapsulating-dml-operations)
该示例演示了如何使用spring将插入、更新、删除封装成可重复使用的Java对象
## [encapsulating-storedprocedures](encapsulating-storedprocedures)
该示例演示了如何使用spring将封装成存储过程
