package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.application.exceptions.DuplicatedFriendShipRequestException;
import com.schibsted.spain.friends.application.exceptions.UnauthorizedFriendshipActionException;
import com.schibsted.spain.friends.application.repositoryInterfaces.FriendshipRepository;

import java.util.Collection;

public class FriendshipService {

    private UserService userService;
    private FriendshipRepository friendshipRepository;

    public FriendshipService(UserService userService, FriendshipRepository friendshipRepository) {
        this.userService = userService;
        this.friendshipRepository = friendshipRepository;
    }

    public void requestFriendship(String requester, String requested)
        throws UnauthorizedFriendshipActionException, DuplicatedFriendShipRequestException {

        verifyUsers(requester, requested);

        if (requester.equals(requested)) {
            throw new UnauthorizedFriendshipActionException(requester);
        }

        if (friendshipRepository.existsFriendshipRequest(requester, requested)) {
            throw new DuplicatedFriendShipRequestException(requester, requested);
        }

        if (friendshipRepository.existsFriendship(requester, requested)) {
            throw new UnauthorizedFriendshipActionException(requested, requester);
        }

        friendshipRepository.addFriendshipRequest(requester, requested);
    }

    public void acceptFriendShip(String requester, String requested)
        throws UnauthorizedFriendshipActionException {

        verifyUsers(requester, requested);

        if (friendshipRepository.existsFriendship(requester, requested)) {
            throw new UnauthorizedFriendshipActionException(requested, requester);
        }

        if (!friendshipRepository.existsFriendshipRequest(requester, requested)) {
            throw new UnauthorizedFriendshipActionException(requested, requester);
        }

        friendshipRepository.addFriendship(requester, requested);
    }

    public void declineFrienshipRequest(String requester, String requested)
        throws UnauthorizedFriendshipActionException {

        verifyUsers(requester, requested);

        if (!friendshipRepository.existsFriendshipRequest(requester, requested)) {
            throw new UnauthorizedFriendshipActionException(requested, requester);
        }

        friendshipRepository.declineFriendshipRequest(requester, requested);
    }

    public Collection<String> getFriends(String username)
        throws UnauthorizedFriendshipActionException {

        verifyUser(username);
        return friendshipRepository.getFriends(username);
    }

    private void verifyUsers(String requester, String requested)
            throws UnauthorizedFriendshipActionException {

        if (!userService.exists(requester)) {
            throw new UnauthorizedFriendshipActionException(requester);
        }

        if (!userService.exists(requested)) {
            throw new UnauthorizedFriendshipActionException(requested);
        }
    }

    private void verifyUser(String username)
        throws UnauthorizedFriendshipActionException {

        if (!userService.exists(username)) {
            throw new UnauthorizedFriendshipActionException(username);
        }
    }
}
