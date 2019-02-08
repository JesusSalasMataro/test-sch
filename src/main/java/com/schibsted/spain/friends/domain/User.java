package com.schibsted.spain.friends.domain;

public class User {
    private String username;
    private Password password;

    public User(String username, Password password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
