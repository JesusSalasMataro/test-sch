package com.schibsted.spain.friends.application.exceptions;

public class UnauthorizedFriendshipActionException extends Exception {

    public UnauthorizedFriendshipActionException(String username) {
        super("User " + username + " is not registered.");
    }

    public UnauthorizedFriendshipActionException(String requester, String requested) {
        super("Users " + requester + " and " + requested + " are already friends.");
    }

}
