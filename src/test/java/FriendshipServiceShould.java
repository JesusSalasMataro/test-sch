import com.schibsted.spain.friends.application.FriendshipRepository;
import com.schibsted.spain.friends.application.FriendshipService;
import com.schibsted.spain.friends.application.UserService;
import com.schibsted.spain.friends.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.exceptions.UnauthorizedFriendShipException;
import com.schibsted.spain.friends.exceptions.UserAlreadyExistsException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class FriendshipServiceShould {

    private static UserService userService;
    private static FriendshipRepository friendshipRepository;
    private static FriendshipService sut;

    @BeforeClass
    public static void numOnceBeforeEachTest() {
        userService = mock(UserService.class);
        friendshipRepository = mock(FriendshipRepository.class);
        doNothing().when(friendshipRepository).saveFriendshipRequest(isA(String.class), isA(String.class));
        sut = new FriendshipService(userService, friendshipRepository);
    }

    @Test
    public void allow_a_friendsihp_request_if_both_users_are_registered() throws InvalidUsernameException, UserAlreadyExistsException, InvalidPasswordException, UnauthorizedFriendShipException {
        String requesterUsername = "jesus";
        String requestedUsername = "joana";

        when(userService.exists(isA(String.class))).thenReturn(true);

        sut.RequestFriendship(requesterUsername, requestedUsername);

        Mockito.verify(friendshipRepository, times(1))
                .saveFriendshipRequest(isA(String.class), isA(String.class));
    }

    @Test(expected = UnauthorizedFriendShipException.class)
    public void dont_allow_a_friendship_request_if_the_requester_user_is_not_registered() throws UnauthorizedFriendShipException {
        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(requesterUsername)).thenReturn(false);
        when(userService.exists(requestedUsername)).thenReturn(true);

        sut.RequestFriendship(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendShipException.class)
    public void dont_allow_a_friendship_request_if_the_requested_user_is_not_registered() throws UnauthorizedFriendShipException {
        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(requesterUsername)).thenReturn(true);
        when(userService.exists(requestedUsername)).thenReturn(false);

        sut.RequestFriendship(requesterUsername, requestedUsername);
    }

    @Test(expected = UnauthorizedFriendShipException.class)
    public void dont_allow_a_friendship_request_from_an_user_to_himself() throws UnauthorizedFriendShipException {
        String username = "jesus";
        when(userService.exists(username)).thenReturn(true);

        sut.RequestFriendship(username, username);
    }

    @Test(expected = UnauthorizedFriendShipException.class)
    public void dont_allow_a_friendship_request_from_an_user_with_pending_request_from_him() throws UnauthorizedFriendShipException {
        String requesterUsername = "jesus";
        String requestedUsername = "joana";
        when(userService.exists(requesterUsername)).thenReturn(true);
        when(userService.exists(requestedUsername)).thenReturn(true);

        sut.RequestFriendship(requesterUsername, requestedUsername);
        sut.RequestFriendship(requesterUsername, requestedUsername);
    }

}
