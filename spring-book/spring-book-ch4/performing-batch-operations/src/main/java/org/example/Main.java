package org.example;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ch4Configuration.class);
		
		AccountDao accountDao = applicationContext.getBean(AccountDao.class);
		
		Account account = accountDao.find(100L);
		
		account.setBalance(20.0);
		
		accountDao.update(List.of(account));
		
		account = accountDao.find(100L);
		
		System.out.println(account.getId());
		System.out.println(account.getOwnerName());
		System.out.println(account.getBalance());
		System.out.println(account.getAccessTime());
		System.out.println(account.isLocked());
	}
}
