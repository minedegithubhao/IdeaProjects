# 正则表达式
![img.png](img.png)
* `^`表示匹配字符串的开头
```sql
-- 表示字符串'abcdefg'是否以字符'a'开始。1-匹配，0-不匹配
select 'abcdefg' regexp '^a';
```
* `$`表示匹配字符的串的结尾
```sql
-- 表示字符串'abcdefg'是否以字符'g'结尾。1-匹配，0-不匹配
select 'abcdefg' regexp 'g$';
```
* `.`表示匹配任意一个字符
```sql
-- 表示字符串'abcdefg'是否以包含字符'h','f'。1-匹配，0-不匹配
select 'abcdefg' regexp '.h', 'abcdefg' regexp '.f';
```
* `[...]`表示匹配括号内的任意字符
```sql
-- 表示字符串'abcdefg'是否包含'w','c','x'中任意一个。1-匹配，0-不匹配
select 'abcdefg' regexp '[wcx]'
```
* `[^...]`表示不匹配括号内的任意字符，和`[...]`刚好相反
```sql
-- 表示字符串'efg'不匹配'XYZ'中任意字符。1-是，0-否
select 'efg' regexp '[^XYZ]','X' regexp '[^XYZ]'
```
## SQL优化
### 使用`show stauts` 命令了解各种SQL的执行频率
```sql
-- 显示当前session中所有的统计参数的值
show status like 'Com_%';
-- 显示系统中所有的统计参数值
show global status like 'Com_%';
```
* Com_select 执行select操作的次数，一次查询只累加1。
* Com_insert 执行insert操作的次数，一次查询只累加1。批量插入也只累加1
* Com_update 执行update操作的次数，
* Com_delete 执行删除操作的次数
* Com_commit 事务提交的次数
* Com_rollback 事务回滚的次数
```sql
-- 获取数据库服务器启用以来的系统信息
show status like 'Innode_%';
```
* Innodb_rows_read：select查询返回的行数。
* Innodb_rows_inserted：执行insert操作插入的行数。
* Innodb_rows_updated：执行update更新的行数
* Innodb_rows_deleted：执行delete执行删除的行数
>通过`Innodb_rows_read`和`Innodb_rows_inserted`能够看出来数据库是以查询为主还是插入为主
```sql
-- 尝试连接MySQL数据库的次数
show global status like 'Connections';
-- 服务器工作时间（秒）
show global status like 'Uptime';
```
### 查询数据库连接信息
```sql
-- 当前数据库的连接信息
show processlist;
```
### 慢查询
```sql
-- 慢查询的次数
show global status like 'Slow_queries';
-- slow_query_log = ON 表示慢查询日志已经开启
show variables like 'slow_query_log';
-- long_query_time = 1.000000 表示慢查询的时间阈值被设置为1秒
show variables like 'long_query_time';
-- 查询1天内的所有慢查询SQL
SELECT * FROM mysql.slow_log WHERE start_time > NOW() - INTERVAL 1 DAY;
```
>通过`slow_query_log`和`long_query_time`来查看是否开启慢查询日志和慢查询阈值。
