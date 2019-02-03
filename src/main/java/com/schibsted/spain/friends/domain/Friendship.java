package com.schibsted.spain.friends.domain;

public class Friendship {

    private String requesterUsername;
    private String requestedUsername;

    public String getRequester() {
        return requesterUsername;
    }

    public String getRequested() {
        return requestedUsername;
    }

    public Friendship(String requester, String requested) {
        this.requesterUsername = requester;
        this.requestedUsername = requested;
    }

}
