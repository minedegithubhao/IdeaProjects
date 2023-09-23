package org.example;

import org.springframework.beans.factory.FactoryBean;

/**
 * 运行时，如果Spring实现了FactoryBean接口，则会调用getObject()来创建该Bean，所创建Bean的类型由getObjectType()返回
 * @author cxd
 */
public class FooFactoryBean implements FactoryBean<Foo> {

	@Override
	public Foo getObject() throws Exception {
		Foo foo = new Foo();
		foo.setName("foo5");
		return foo;
	}

	@Override
	public Class<?> getObjectType() {
		return Foo.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
