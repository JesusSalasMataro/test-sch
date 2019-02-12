package com.schibsted.spain.friends.domain;

import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;

public class User {

    private String username;
    private Password password;

    public User(String username, String password, FieldValidatorService validatorService)
        throws InvalidUsernameException, InvalidPasswordException {

        validatorService.validateUsername(username);
        validatorService.validatePassword(password);
        this.username = username;
        this.password = new Password(password);
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
