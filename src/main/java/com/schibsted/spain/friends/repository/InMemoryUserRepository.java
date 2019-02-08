package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.application.UserRepository;
import com.schibsted.spain.friends.domain.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

    List<User> users;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public boolean exists(String username) {
        return users.stream()
            .anyMatch(u -> u.getUsername().equals(username));
    }

    @Override
    public boolean isValid(User user) {
        return users.stream()
            .anyMatch(u ->
                u.getUsername().equals(user.getUsername()) &&
                u.getPassword().equals(user.getPassword())
            );
    }
}
