package com.schibsted.spain.friends.repository;

public class FriendshipDTO {
    private String requesterUsername;
    private String requestedUsername;

    public String getRequester() {
        return requesterUsername;
    }

    public void setRequester(String username) {
        this.requesterUsername = username;
    }

    public String getRequested() {
        return requestedUsername;
    }

    public void setRequested(String username) {
        this.requestedUsername = username;
    }

    public FriendshipDTO(String requester, String requested) {
        this.requesterUsername = requester;
        this.requestedUsername = requested;
    }
}
