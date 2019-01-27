package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.application.FriendshipRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryFriendshipRepository implements FriendshipRepository {

    List<FriendshipDTO> friendships;

    public InMemoryFriendshipRepository() {
        friendships = new ArrayList<>();
    }

    @Override
    public void saveFriendshipRequest(String requesterUsername, String requestedUsername) {
        FriendshipDTO friendship = new FriendshipDTO(requesterUsername, requestedUsername);
        friendships.add(friendship);
    }

    @Override
    public boolean existsFriendshipRequest(String requester, String requested) {
        return false;
    }
}
