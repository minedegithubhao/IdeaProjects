package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class Ch2Configuration {
	
	@Bean
	@Profile("dev")
	public Foo devFoo(@Value("${name}") String name) {
		Foo foo = new Foo();
		foo.setName("dev " + name);
		return foo;
	}
	
	@Bean
	@Profile("prod")
	public Foo prodFoo(@Value("${name}") String name) {
		Foo foo = new Foo();
		foo.setName("prod " + name);
		return foo;
	}

	/**
	 * 该方法将启用属性占位符解析机制
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
