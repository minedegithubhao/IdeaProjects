package org.example.exception;

/**
 * User: mertcaliskan
 * Date: 12/06/14
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String name) {
        super("User not found with name: " + name);
    }
}