package com.schibsted.spain.friends.repository;

public class Friendship {

    private String username;
    private String friendUsername;

    public String getUsername() {
        return username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public Friendship(String username, String friendUsername) {
        this.username = username;
        this.friendUsername = friendUsername;
    }

}
