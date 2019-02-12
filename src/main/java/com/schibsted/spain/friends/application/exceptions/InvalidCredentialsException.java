package com.schibsted.spain.friends.application.exceptions;

public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException(String username) {
        super("Invalid credentials for user: " + username);
    }
}
