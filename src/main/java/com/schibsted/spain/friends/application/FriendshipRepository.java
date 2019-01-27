package com.schibsted.spain.friends.application;

public interface FriendshipRepository {

    void saveFriendshipRequest(String requester, String requested);
    boolean existsFriendshipRequest(String requester, String requested);
}
