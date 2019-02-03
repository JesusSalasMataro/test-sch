import com.schibsted.spain.friends.application.FriendshipRepository;
import com.schibsted.spain.friends.application.FriendshipService;
import com.schibsted.spain.friends.application.UserService;
import com.schibsted.spain.friends.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class FriendshipServiceShould {

    private static UserService userService;
    private static FriendshipRepository friendshipRepository;
    private static FriendshipService sut;

    @Before
    public void beforeEachTest() {
        userService = mock(UserService.class);
        friendshipRepository = mock(FriendshipRepository.class);
        doNothing().when(friendshipRepository).addFriendshipRequest(isA(String.class), isA(String.class));
        sut = new FriendshipService(userService, friendshipRepository);
    }

    @Test
    public void allow_a_friendsihp_request_if_both_users_are_registered()
        throws UnauthorizedFriendshipActionException, DuplicatedFriendShipRequestException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);

        sut.requestFriendship(requesterUsername, requestedUsername);

        Mockito.verify(friendshipRepository, times(1))
            .addFriendshipRequest(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendshipActionException.class)
    public void not_allow_a_friendship_request_if_the_requester_user_is_not_registered()
        throws UnauthorizedFriendshipActionException, DuplicatedFriendShipRequestException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(requesterUsername)).thenReturn(false);
        when(userService.exists(requestedUsername)).thenReturn(true);

        sut.requestFriendship(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendshipActionException.class)
    public void not_allow_a_friendship_request_if_the_requested_user_is_not_registered()
        throws UnauthorizedFriendshipActionException, DuplicatedFriendShipRequestException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(requesterUsername)).thenReturn(true);
        when(userService.exists(requestedUsername)).thenReturn(false);

        sut.requestFriendship(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendshipActionException.class)
    public void not_allow_a_friendship_request_from_an_user_to_himself()
        throws UnauthorizedFriendshipActionException, DuplicatedFriendShipRequestException {

        String username = "jesus";
        when(userService.exists(username)).thenReturn(true);

        sut.requestFriendship(username, username);
    }

    @Test(expected = DuplicatedFriendShipRequestException.class)
    public void not_allow_a_friendship_request_from_an_user_with_pending_request_from_him()
        throws UnauthorizedFriendshipActionException, DuplicatedFriendShipRequestException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendshipRequest(requesterUsername, requestedUsername)).thenReturn(true);

        sut.requestFriendship(requesterUsername, requestedUsername);
    }

    @Test
    public void set_users_as_friends_when_requested_friendship_is_accepted()
        throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendshipRequest(requesterUsername, requestedUsername)).thenReturn(true);

        sut.acceptFriendShip(requesterUsername, requestedUsername);

        Mockito.verify(friendshipRepository, times(1))
            .addFriendship(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendshipActionException.class)
    public void not_allow_request_friendship_between_friends()
        throws DuplicatedFriendShipRequestException, UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendship(requesterUsername, requestedUsername)).thenReturn(true);

        sut.requestFriendship(requesterUsername, requestedUsername);
    }

    @Test
    public void allow_registered_users_decline_a_friendship_request()
        throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendshipRequest(requesterUsername, requestedUsername)).thenReturn(true);

        sut.declineFrienshipRequest(requesterUsername, requestedUsername);

        Mockito.verify(friendshipRepository, times(1))
            .declineFriendshipRequest(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendshipActionException.class)
    public void not_allow_unregistered_users_decline_a_friendship_request()
        throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(false);

        sut.declineFrienshipRequest(requesterUsername, requestedUsername);
    }

    @Test
    public void allow_list_friends_of_a_registered_user() {

        String username = "jesus";

        sut.getFriends(username);

        Mockito.verify(friendshipRepository, times(1))
            .getFriends(username);
    }

    @Test(expected = UnauthorizedFriendshipActionException.class)
    public void not_allow_accept_friendship_request_already_accepted()
        throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendship(requesterUsername, requestedUsername)).thenReturn(true);

        sut.acceptFriendShip(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendshipActionException.class)
    public void not_allow_accept_friendship_without_a_previous_friendship_request()
            throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendshipRequest(requesterUsername, requestedUsername)).thenReturn(false);

        sut.acceptFriendShip(requesterUsername, requestedUsername);

        Mockito.verify(friendshipRepository, times(1))
            .addFriendshipRequest(requesterUsername, requestedUsername);
    }

    @Test
    public void allow_accept_friendship_after_friendship_request()
        throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendshipRequest(requesterUsername, requestedUsername)).thenReturn(true);

        sut.acceptFriendShip(requesterUsername, requestedUsername);

        Mockito.verify(friendshipRepository, times(1))
            .addFriendship(requesterUsername, requestedUsername);
    }

    @Test
    public void allow_accept_friendship_after_friendship_request_with_any_users_order()
        throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendshipRequest(requestedUsername, requesterUsername)).thenReturn(true);

        sut.acceptFriendShip(requestedUsername, requesterUsername);

        Mockito.verify(friendshipRepository, times(1))
            .addFriendship(requestedUsername, requesterUsername);
    }

    @Test
    public void allow_decline_friendship_after_friendship_request_with_any_users_order()
        throws UnauthorizedFriendshipActionException {

        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(isA(String.class))).thenReturn(true);
        when(friendshipRepository.existsFriendshipRequest(requestedUsername, requesterUsername)).thenReturn(true);

        sut.declineFrienshipRequest(requestedUsername, requesterUsername);

        Mockito.verify(friendshipRepository, times(1))
            .declineFriendshipRequest(requestedUsername, requesterUsername);
    }
}
