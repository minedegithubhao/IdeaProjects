package org.example;

import org.springframework.stereotype.Component;

@Component
public class Foo {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
