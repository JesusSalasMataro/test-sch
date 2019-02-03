package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.domain.FriendshipRequest;

import java.util.List;

public interface FriendshipRepository {

    void addFriendshipRequest(String requester, String requested);

    void addFriendship(String requester, String requested);

    boolean existsFriendshipRequest(String requester, String requested);

    boolean existsFriendship(String requester, String requested);

    void declineFriendshipRequest(String requesterUsername, String requestedUsername);

}
