package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.application.FriendshipRepository;
import com.schibsted.spain.friends.domain.Friendship;
import com.schibsted.spain.friends.domain.FriendshipRequest;

import java.util.ArrayList;
import java.util.Collection;

public class InMemoryFriendshipRepository implements FriendshipRepository {

    Collection<FriendshipRequest> friendshipsRequests;
    Collection<Friendship> friendships;

    public InMemoryFriendshipRepository() {
        friendshipsRequests = new ArrayList<>();
        friendships = new ArrayList<>();
    }

    @Override
    public void addFriendshipRequest(String requesterUsername, String requestedUsername) {
        FriendshipRequest friendship = new FriendshipRequest(requesterUsername, requestedUsername);
        friendshipsRequests.add(friendship);
    }

    @Override
    public void addFriendship(String requesterUsername, String requestedUsername) {
        Friendship friendship = new Friendship(requesterUsername, requestedUsername);
        friendships.add(friendship);
    }

    @Override
    public boolean existsFriendshipRequest(String requesterUsername, String requestedUsername) {
        return friendshipsRequests.stream()
            .anyMatch(friendship ->
                friendship.getRequester().equals(requesterUsername) &&
                friendship.getRequested().equals(requestedUsername) &&
                !friendship.isDeclined()
            );
    }

    @Override
    public boolean existsFriendship(String requesterUsername, String requestedUsername) {
        return friendships.stream()
            .anyMatch(friendship ->
                friendship.getRequester().equals(requesterUsername) &&
                friendship.getRequested().equals(requestedUsername)
            );
    }

    @Override
    public void declineFriendshipRequest(String requesterUsername, String requestedUsername) {
        friendshipsRequests.stream()
            .filter(friendshipRequest ->
                friendshipRequest.getRequester().equals(requesterUsername) &&
                friendshipRequest.getRequested().equals(requestedUsername) &&
                !friendshipRequest.isDeclined()
            )
            .findAny()
            .get()
            .decline();
    }

}
