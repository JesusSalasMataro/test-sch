package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.domain.User;

public interface UserRepository {

    void save (User user);

    boolean exists(String username);

    boolean isValid(User user);
}
