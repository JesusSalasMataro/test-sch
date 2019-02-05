package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;
import com.schibsted.spain.friends.domainservices.SecurityService;
import com.schibsted.spain.friends.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.exceptions.UserAlreadyExistsException;

public class UserService {
    private FieldValidatorService validator;
    private SecurityService securityService;
    private UserRepository userRepository;

    public UserService (FieldValidatorService validator, SecurityService securityService,
                        UserRepository userRepository) {

        this.validator = validator;
        this.securityService = securityService;
        this.userRepository = userRepository;
    }

    public void registerNewUser(String username, String password)
            throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {

        validator.validateUsername(username);
        validator.validatePassword(password);

        password = securityService.encript(password);

        if (exists(username)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(username, password);
        userRepository.save(user);
    }

    public boolean exists(String username) {
        return userRepository.exists(username);
    }
}