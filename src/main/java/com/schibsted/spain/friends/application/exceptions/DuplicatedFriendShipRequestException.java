package com.schibsted.spain.friends.application.exceptions;

public class DuplicatedFriendShipRequestException extends Exception {
    public DuplicatedFriendShipRequestException(String requesterUsername, String requestedUsername) {
        super("User " + requesterUsername + " already has a friendship request to " + requestedUsername);
    }
}
