create table t(name varchar(20), email varchar(40));
insert into t value ('beijing', 'beijing@163.com');
insert into t value ('beijing126', 'beijing@126.com');
insert into t value ('beijing188', 'beijing@188.com');
insert into t value ('beijing168', 'beijing@168.com');
insert into t value ('beijing288', 'beijing@288.com');

create table sales(
    year int not null ,
    country varchar(20) not null ,
    product varchar(32) not null ,
    profit int
);
insert into sales values(2004, 'china', 'tnt1', 2001);
insert into sales values(2004, 'china', 'tnt2', 2002);
insert into sales values(2004, 'china', 'tnt3', 2003);
insert into sales values(2005, 'china', 'tnt1', 2004);
insert into sales values(2005, 'china', 'tnt2', 2005);
insert into sales values(2005, 'china', 'tnt3', 2006);
insert into sales values(2005, 'china', 'tnt1', 2007);
insert into sales values(2005, 'china', 'tnt2', 2008);
insert into sales values(2005, 'china', 'tnt3', 2009);
insert into sales values(2006, 'china', 'tnt1', 2010);
insert into sales values(2006, 'china', 'tnt2', 2011);
insert into sales values(2006, 'china', 'tnt3', 2012);