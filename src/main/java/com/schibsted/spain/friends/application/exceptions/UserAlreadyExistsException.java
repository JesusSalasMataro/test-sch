package com.schibsted.spain.friends.application.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
