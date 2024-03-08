package org.example;

import java.util.Collections;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(Ch2Configuration.class);

		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		// 设置profile
		environment.setActiveProfiles("prod");

		// 获取了 Spring 的环境（environment）中的属性源（PropertySources）。属性源是用于存储应用程序配置属性的地方。
		MutablePropertySources propertySources = environment.getPropertySources();
		// 这行代码创建了一个名为 "mapSource" 的自定义属性源（MapPropertySource），并向其中添加了一个属性。在这个示例中，属性的名称是 "name"，值是 "your foo"。这意味着您向应用程序的配置中添加了一个名为 "name" 的属性，其值为 "your foo"。
		propertySources.addLast(new MapPropertySource("mapSource", Collections.singletonMap("name", (Object)"your foo")));
		// 这行代码刷新了 Spring 应用程序上下文，包含了所有的 bean 定义和它们的依赖关系
		applicationContext.refresh();
		
		Foo foo = applicationContext.getBean(Foo.class);
		System.out.println(foo.getName());
	}

}
