例如：
```sql
select * from t where email regexp '@163[.,]com$';
-- 等价于
select * from t where email like '%@163.com' or email like '%@163,com'
```
## 随机数rand()
```sql
-- 随机获取数据
select * from t order by rand();

-- 随机获取3条数据
select * from t order by rand() limit 3;
```
## 使用group by的with rollup获取更多信息
```sql
select year,country,product, sum(profit) from sales group by year,country,product;
-- 获取更多统计信息
select year,country,product, sum(profit) from sales group by year,country,product with rollup ;
```
