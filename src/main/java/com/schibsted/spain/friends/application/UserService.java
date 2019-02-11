package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.Password;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;
import com.schibsted.spain.friends.application.exceptions.InvalidCredentialsException;
import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.application.exceptions.UserAlreadyExistsException;
import com.schibsted.spain.friends.application.repositoryInterfaces.UserRepository;

public class UserService {

    private FieldValidatorService validator;
    private UserRepository userRepository;

    public UserService (FieldValidatorService validator, UserRepository userRepository) {

        this.validator = validator;
        this.userRepository = userRepository;
    }

    public void registerNewUser(String username, String plainTextPassword)
            throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {

        validator.validateUsername(username);
        validator.validatePassword(plainTextPassword);

        Password password = new Password(plainTextPassword);

        if (exists(username)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(username, password);
        userRepository.save(user);
    }


    public boolean exists(String username) {
        return userRepository.exists(username);
    }

    public void validate(User user) throws InvalidCredentialsException {
        if (!userRepository.isValid(user)) {
            throw new InvalidCredentialsException(user);
        }
    }
}