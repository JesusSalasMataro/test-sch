package com.schibsted.spain.friends.domain;

import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;

public class User {

    private Username username;
    private Password password;
    
    public User(String username, String password) throws InvalidUsernameException, InvalidPasswordException {
        this.username = new Username(username);
        this.password = new Password(password);
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
