package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.application.exceptions.InvalidCredentialsException;
import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.application.exceptions.UserAlreadyExistsException;
import com.schibsted.spain.friends.application.repositoryInterfaces.UserRepository;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;

public class UserService {

    private FieldValidatorService validatorService;
    private UserRepository userRepository;

    public UserService (FieldValidatorService validatorService, UserRepository userRepository) {

        this.validatorService = validatorService;
        this.userRepository = userRepository;
    }

    public void registerNewUser(String username, String password)
        throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {

        if (exists(username)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(username, password, validatorService);
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