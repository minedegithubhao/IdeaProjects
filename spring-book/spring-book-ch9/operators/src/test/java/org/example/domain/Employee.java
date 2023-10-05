package org.example.domain;

/**
 * Created by mertcaliskan
 * on 10/08/14.
 */
public class Employee {

    private String name;
    private Address address;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}
