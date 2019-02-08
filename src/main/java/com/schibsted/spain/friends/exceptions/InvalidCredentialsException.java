package com.schibsted.spain.friends.exceptions;

import com.schibsted.spain.friends.domain.User;

public class InvalidCredentialsException extends Exception {

    public InvalidCredentialsException(User user) {
        super("Invalid credentials for user: " + user.getUsername());
    }
}
