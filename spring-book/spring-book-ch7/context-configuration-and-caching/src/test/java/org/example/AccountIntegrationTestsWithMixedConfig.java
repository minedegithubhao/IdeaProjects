package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Ch7Configuration.class, AccountIntegrationTestsWithMixedConfig.Config.class})
public class AccountIntegrationTestsWithMixedConfig {

	@Configuration
	@ImportResource("classpath:/applicationContext.xml")
	static class Config {
	}
	
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void accountServiceShouldBeInjected() {
		Assert.assertNotNull(accountService);
	}
}
