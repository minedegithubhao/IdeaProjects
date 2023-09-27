package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Ch7ConfigurationForDependencyInjection.class)
public class DependencyInjectionTests {
	
	@Autowired
	@Qualifier("foo1")
	private Foo foo1;
	
	@Resource
	private Foo foo2;
	
	@Resource
	private Bar bar;
	
	@Test
	public void testInjections() {
		Assert.assertNotNull(foo1);
		Assert.assertNotNull(foo2);
		Assert.assertNotNull(bar);
	}

}
