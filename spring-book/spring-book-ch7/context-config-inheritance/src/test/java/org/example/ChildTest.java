package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:/subContext.xml")
public class ChildTest extends BaseTest {
	@Autowired
	private Bar bar;
	
	@Test
	public void dependenciesShouldBeAvailable() {
		Assert.assertNotNull(foo);
		Assert.assertNotNull(bar);
	}
}
