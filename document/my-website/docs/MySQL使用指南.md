# SQL基础
SQL分类
* DDL 数据定义语言
  * 创建数据库
  ```sql
  -- 创建数据库
  create database dbname;
  -- 查看系统中所有数据库
  show databases;
  -- 选择数据库
  use dbname;
  -- 查看数据库中所有数据表
  show tables;
  ```
  * 删除数据库
  ```sql
  -- 删除数据库
  drop database dbname;
  ```
  * 创建表
  ```sql
  -- 创建表
  create table emp(ename varchar(10), deptno int(2));
  -- 查看表定义
  desc emp;
  -- 查看表定义详细信息
  show create table emp;
  ```
  * 删除表
  ```sql
  -- 删除表
  drop table emp;
  ```
  * 修改表
  ```sql
  -- 修改表类型
  alter table emp modify ename varchar(20); 
  -- 增加表字段
  alter table emp add column age int(3);
  -- 删除表字段
  alter table emp drop column age;
  ```

* DML 数据操作语句
* DCL 数据控制语句

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
>SQL优化的顺序，先定位慢查询SQL，使用explain分析问问题出现的原因，再根据问题进行优化
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
### 通过`explain`分析慢查询语句
```sql
explain select sum(profit) from sales;
```
![img_1.png](img_1.png)
* id：表示执行顺序，id相同时越靠上的先执行。
* select_type：查询的类型
  * SIMPLE：简单表，不使用表连接或者子查询
  * PRIMARY：主查询，也叫外层查询，包含表连接或者子查询
  * SUBQUERY：子查询
  * UNION：`union`中的第二个或后续查询
  * DERIVED：`from`字句中的子查询
* table：查询的的表名
* type：表示表的连接类型，代表来MySQL如何从表中获取数据。性能由好到坏依次如下：
  * system：表中仅有一行，即常量表。（这里是说一张表只有一条数据，放心，基本上实际生产中基本不会出现）
  * const：表中最多由一行匹配，例如主键(primary key)、或者唯一索引(unique key)。
  * eq_ref：对于前面的每一行，在此表中只查询一条记录，简单来说就是多表查询中使用主键(primary key）、或者唯一索引(unique key)关联
  * ref：与eq_ref类似，多表查询中使用普通索引。
  * ref_or_null：与ref类似，区别在于包含对null的查询
  * index_merge：索引合并优化。
  * unique_subquery：in的后面是一个查询主键字段的子查询
  * index_subquery：与unique_subquery类似，in的后面是一个查询非唯一索引字段的子查询。
  * range：按照索引范围扫描
  * index：按照索引顺序扫描，前面的每一行都是通过索引得到数据
  * all：全表扫描，前面的每一行都通过全表扫描得到数据
* possible_keys：表示查询时，可能使用的索引
* key：表示查询时，实际使用的索引
* key_len：索引字段的长度
* rows：扫描行的数量
* filtered：表示查询结果的过滤程度，即在所有行中筛选的出行的百分比
* extra：执行情况的说明和描述
  * Using where：在存储引擎中使用了where过滤结果集
  * Using index：表示MySQL使用用索引覆盖，查询可以只通过索引而不必读取实际的数据行
  * Using temporary：表示MySQL使用了临时表来处理查询
  * Using filesort：表示MySQL需要对结果集进行排序操作
>通常，只需要关注type、key、rows等字段，以确定查询的性能瓶颈在哪里，并据此进行索引优化、查询重写等操作。
## 索引
### 索引的存储分类
MySQL中索引的存储类型目前只有2中（btree和hash），MyISAM和InnoDB存储引擎都支持btree索引。
>MySQL目前不支持函数索引（版本5.0），但是支持字段部分索引，如对字段name的前4位创建索引
```sql
create index ind_company_name on company(name(4));
```
### MySQL中如何使用索引
* 复合索引，只有查询条件中使用了最左边的列，索引才会被使用
```sql
-- 创建索引
create index ind_sales_companyId_money on sales(company_id, money);
-- 查询索引
show index from sales;
-- 该sql使用了复合索引ind_sales_companyId_money中左侧的列索引使用索引，如下图
explain select * from   sales where company_id = 1;
```
![img_2.png](img_2.png)
```sql
-- 该sql使用了复合索引ind_sales_companyId_money中右侧的索引，索引索引未生效，如下图：
explain select * from   sales where money > 3000;
```
![img_3.png](img_3.png)
* like使用索引
```sql
-- 创建索引
create index ind_country on sales(country);
-- 泗洪左like，走了索引
explain select * from sales where country like 'ch%';
```
![img_4.png](img_4.png)
```sql
-- 泗洪右like，索引失效,如下图
explain select * from sales where country like '%ina';
```
![img_5.png](img_5.png)
* null使用索引
```sql
-- 创建索引
create index ind_company_name on company(name);
-- 查看索引
show index from company;
```
![img_6.png](img_6.png)
### 索引失效
* 使用or条件查询时，前面的条件有索引，后面的条件无索引，就会出现索引失效
```sql
-- 查看索引
show index from sales;
-- 删除索引
drop index ind_country on sales
-- 创建索引
create index ind_money on sales(money);
-- money有索引，country无索引，导致索引失效
explain select * from sales where money > 3000 or country = 'china';
```
![img_7.png](img_7.png)
```sql
-- 有索引
explain select * from sales where money > 3000;
```
![img_8.png](img_8.png)
* 复合索引如果查询条件不是索引第一列，将会导致索引失效，参照复合索引案例。
* '%a'，%出现在前面，会导致索引失效。
* 如果索引列字段类型为字符串，使用where查询时一定要用引号括起来，否则会导致索引失效。
```sql
-- 创建索引
create index ind_sales_companyId on sales(company_id);
-- 不加引号，索引失效
explain select * from sales where company_id = 2;
```
![img_9.png](img_9.png)
```sql
-- 加上引号，索引有效
explain select * from sales where company_id = '2';
```
![img_10.png](img_10.png
### 查看索引的使用情况
`show status like 'Handler_read%'`查询结果如下
![img_11.png](img_11.png)
* Handler_read_next：表示范围扫描或者索引扫描的次数
* Handler_read_key： 表示等值查询使用索引的次数。

Handler_read_next 和 Handler_read_key 的比率： 这两个值的比率越高，表示索引的使用越高效

这些值相对于以前的查询有所减少，表示索引的优化对查询性能产生了积极影响。
```sql
SELECT * FROM performance_schema.table_io_waits_summary_by_index_usage
WHERE OBJECT_SCHEMA = 'test' AND OBJECT_NAME = 'sales';
```
* COUNT_STAR： 可以告诉你索引被查询的频率。频繁使用的索引通常是被高效利用的。
* AVG_TIMER_WAIT： 表示了使用该索引的查询的平均等待时间。较低的等待时间通常意味着索引的性能较好。
* SUM_TIMER_WAIT： 显示了所有查询等待时间的总和，用于评估索引的总体性能。

## 常用SQL优化
### insert优化
```sql
-- 方式一
insert into sales values (1,2),(3,4),(5,6);
-- 方式二
insert into sales values (1,2);
insert into sales values (3,4);
insert into sales values (5,6);
```
> 同一个客户端使用方式一比方式二要快好几倍，方式一大大缩减了客户端与数据库之间连接、关闭等消耗
### group by优化
group by 查询的时候，查看执行计划可能会发现`Extral`里面有Using filesort，filesort很耗时，可以是使用`order by null`来避免filesort。
### order by优化
* 使用函数、表达式或别名进行排序
```sql
-- company_id、money均有索引，索引生效
select * from sales order by company_id, money;
-- company_id、money均有索引，order by 顺序与索引顺序相同，且所有列都是升序或降序。
select * from sales order by company_id desc, money desc;
-- company_id、money均有索引，查询条件和 order 使用相同的索引列
select * from sales where company_id = '1' order by company_id desc, money desc;
```
```sql
-- 以下索引失效
-- 不满足所有列均是升序或者降序，索引失效
select * from sales order by company_id asc, money desc;
-- 查询条件和order by 未使用相同的索引，所以失效
select * from sales year = 2022 order by company_id;
```
### 嵌套查询优化
```sql
-- sales.company_id 和 company.id 均有索引
explain select * from sales where company_id not in (select id from company);
```
![img_12.png](img_12.png)
```sql
-- 优化后,效率提高了
explain select * from sales s left join company c on c.id = s.company_id where s.company_id is null;
```
![img_13.png](img_13.png)
### or 优化
三个索引分别是：id、year、company_id 和 year
```sql
-- 建立id、year，下面会使用索引
select * from sales where id = '1' or year = '2006';
-- 建立复合索引company_id 和 year，下面索引失效
select * from sales where company_id = '3' or year = '2006';
```
### 使用 SQL 提示
* using index
```sql
-- 使用索引
select * from sales using index (ind_company_id) where company_id = '1';
-- 忽略索引
select * from sales ignore index (ind_company_id) where company_id = '1';
-- 强制使用索引
select * from sales force index (ind_company_id) where company_id = '1';
```
>using index 和 force index 的区别

using index :这并不是强制使用该索引，MySQL优化器仍然会根据查询条件和索引选择性选择最佳索引

force index :它告诉MySQL优化器强制使用指定的索引来处理查询，即使优化器认为其他索引更合适。这个提示会忽略MySQL优化器的选择，强制使用指定的索引进行查询.

# 附录
## 查询表间使用
* oracle
```sql
select A.tablespace_name,
       A.total/1024/1024 as 总大小_mb,
       B.total/1024/1024 as 剩余大小_mb,
       (1 - B.total/A.total) * 100 || '%' as 已用百分比,
from (select tablespace_name, sum(bytes) total from dba_data_files group by tablespace_name) A,
     (select tablespace_name, sum(bytes) total from dba_free_files group by tablespace_name) B
where A.tablespace_name = B.tablespace_name;
```
## 数据文件导出
* MySQL
```sql
select 
    col.table_schame as '数据库',
    upper(col.table_name) as '表',
    tab.table_comment as '表备注',
    upper(col.column_name) as '字段',
    upper(col.column_type) as '数据类型'，
    case when upper(col.column_key) = 'PRI' then 'TRUE' else 'FALSE' end as '是否主键',
    case when upper(col.is_nullable) = 'YES' then 'TRUE' else 'FALSE' end as '是否可以为空',
    col.column_comment as '注释',
    case when col.data_type = 'int' then col.numeric_percision else col.character_maxinum_length end as '字段长度'
from information_schema.columns as col 
    join information_schema.tables as tab 
    on  col.table_schame = tab.table_schame and col.table_name = tab.table_name
```



