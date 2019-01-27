package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.User;

public interface UserRepository {
    void save (User user);
    boolean exists(String username);
}
