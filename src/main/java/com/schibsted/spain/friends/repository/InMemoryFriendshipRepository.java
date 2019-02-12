package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.application.repositoryInterfaces.FriendshipRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemoryFriendshipRepository implements FriendshipRepository {

    protected Collection<FriendshipRequest> friendshipsRequests;
    protected Collection<Friendship> friendships;

    public InMemoryFriendshipRepository() {
        friendshipsRequests = new ArrayList<>();
        friendships = new ArrayList<>();
    }

    @Override
    public void addFriendshipRequest(String requesterUsername, String requestedUsername) {
        FriendshipRequest friendshipRequest = new FriendshipRequest(requesterUsername, requestedUsername);
        friendshipsRequests.add(friendshipRequest);
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
                (
                    friendship.getRequester().equals(requesterUsername) &&
                    friendship.getRequested().equals(requestedUsername) &&
                    !friendship.isDeclined()
                )
                ||
                (
                    friendship.getRequester().equals(requestedUsername) &&
                    friendship.getRequested().equals(requesterUsername) &&
                    !friendship.isDeclined()
                )
            );
    }

    @Override
    public boolean existsFriendship(String requesterUsername, String requestedUsername) {
        return friendships.stream()
            .anyMatch(friendship ->
                (
                    friendship.getUsername().equals(requesterUsername) &&
                    friendship.getFriendUsername().equals(requestedUsername)
                )
                ||
                (
                    friendship.getFriendUsername().equals(requesterUsername) &&
                    friendship.getUsername().equals(requestedUsername)
                )
            );
    }

    @Override
    public void declineFriendshipRequest(String requesterUsername, String requestedUsername) {
        friendshipsRequests.stream()
            .filter(friendshipRequest ->
                (
                    friendshipRequest.getRequester().equals(requesterUsername) &&
                    friendshipRequest.getRequested().equals(requestedUsername) &&
                    !friendshipRequest.isDeclined()
                )
                ||
                (
                    friendshipRequest.getRequested().equals(requesterUsername) &&
                    friendshipRequest.getRequester().equals(requestedUsername) &&
                    !friendshipRequest.isDeclined()
                )
            )
            .findAny()
            .get()
            .decline();
    }

    @Override
    public Collection<String> getFriends(String username) {
        return Stream.concat(
            friendships.stream()
                .filter(friendship -> friendship.getUsername().equals(username))
                .map(friendship -> friendship.getFriendUsername()),
            friendships.stream()
                .filter(friendship -> friendship.getFriendUsername().equals(username))
                .map(friendship -> friendship.getUsername())
            )
            .collect(Collectors.toList()
        );
    }

}
