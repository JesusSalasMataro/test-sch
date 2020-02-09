package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.application.repositoryInterfaces.UserRepository;
import com.schibsted.spain.friends.domain.Password;
import com.schibsted.spain.friends.domain.User;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryUsersRepository implements UserRepository {

    protected Collection<User> users;

    public InMemoryUsersRepository() {
        users = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public boolean exists(String username) {
        return users.stream()
            .anyMatch(user -> user.getUsername().asString().equals(username));
    }

    @Override
    public boolean exists(String username, Password password) {
        return users.stream()
            .anyMatch(user ->
                user.getUsername().asString().equals(username) &&
                user.getPassword().equals(password)
            );
    }

}
