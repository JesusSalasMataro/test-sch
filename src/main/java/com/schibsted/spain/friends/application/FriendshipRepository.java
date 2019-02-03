package com.schibsted.spain.friends.application;

import java.util.Collection;

public interface FriendshipRepository {

    void addFriendshipRequest(String requester, String requested);

    void addFriendship(String requester, String requested);

    boolean existsFriendshipRequest(String requester, String requested);

    boolean existsFriendship(String requester, String requested);

    void declineFriendshipRequest(String requesterUsername, String requestedUsername);

    Collection<String> getFriends(String username);
}
