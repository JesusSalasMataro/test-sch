package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.application.repositoryInterfaces.UserRepository;
import com.schibsted.spain.friends.domain.User;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryUsersRepository implements UserRepository {

    protected Collection<BdUserDto> users;

    public InMemoryUsersRepository() {
        users = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        BdUserDto userDto = new BdUserDto(user.getUsername(), user.getPassword());
        users.add(userDto);
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
