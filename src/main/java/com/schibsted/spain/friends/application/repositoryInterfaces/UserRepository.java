package com.schibsted.spain.friends.application.repositoryInterfaces;

import com.schibsted.spain.friends.domain.Password;
import com.schibsted.spain.friends.domain.User;

public interface UserRepository {

    void save (User user);

    boolean exists(String username);

    boolean exists(String username, Password password);

}
