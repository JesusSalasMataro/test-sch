package com.schibsted.spain.friends.application;

import com.schibsted.spain.friends.exceptions.DuplicatedFriendShipRequestException;
import com.schibsted.spain.friends.exceptions.UnauthorizedFriendShipException;

public class FriendshipService {

    private UserService userService;
    private FriendshipRepository friendshipRepository;

    public FriendshipService(UserService userService, FriendshipRepository friendshipRepository) {
        this.userService = userService;
        this.friendshipRepository = friendshipRepository;
    }

    public void RequestFriendship(String requester, String requested) throws UnauthorizedFriendShipException, DuplicatedFriendShipRequestException {
        if (requester.equals(requested)) {
            throw new UnauthorizedFriendShipException(requester);
        }

        if (!userService.exists(requester)) {
            throw new UnauthorizedFriendShipException(requester);
        }

        if (!userService.exists(requested)) {
            throw new UnauthorizedFriendShipException(requested);
        }

        if (friendshipRepository.existsFriendshipRequest(requester, requested)) {
            throw new DuplicatedFriendShipRequestException(requester, requested);
        }

        friendshipRepository.saveFriendshipRequest(requester, requested);
    }
}
