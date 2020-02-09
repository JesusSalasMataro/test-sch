package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.application.exceptions.InvalidCredentialsException;
import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.application.exceptions.UserAlreadyExistsException;
import com.schibsted.spain.friends.application.repositoryInterfaces.UserRepository;
import com.schibsted.spain.friends.domain.Password;
import com.schibsted.spain.friends.domain.User;

public class UserService {

    private UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(String username, String password)
        throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {

        if (exists(username)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(username, password);
        userRepository.save(user);
    }

    public boolean exists(String username) {
        return userRepository.exists(username);
    }

    public boolean exists(String username, Password password) {
        return userRepository.exists(username, password);
    }
}
