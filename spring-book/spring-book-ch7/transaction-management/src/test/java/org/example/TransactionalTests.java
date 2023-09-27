package org.example;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
@Transactional
//@TransactionConfiguration(transactionManager = "myTxMgr", defaultRollback = false)
public class TransactionalTests {

	@Autowired
	private SessionFactory sessionFactory;

	@BeforeTransaction
	public void setUp() {
		// setup code that will run before transaction initiation...
	}

	@AfterTransaction
	public void tearDown() {
		// cleanup code that will run after transaction completion
	}

	@Test
	@Rollback(false)
	public void testMethod1() {
		// ...
	}

	@Test
	public void testMethod2() {
		// 这里进行了手动可以强制刷新，虽然Spring进行了回滚，但是数据已经更新到了数据库
		sessionFactory.getCurrentSession().flush();
	}
}
