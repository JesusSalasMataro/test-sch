package com.schibsted.spain.friends.exceptions;

public class UnauthorizedFriendShipException extends Exception {
    public UnauthorizedFriendShipException(String username) {
        super("User " + username + " is not registered.");
    }
}
