package integration;

import com.schibsted.spain.friends.domain.Friendship;
import com.schibsted.spain.friends.domain.FriendshipRequest;
import helpers.ExtendedInMemoryFriendsRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Iterables;

import java.util.Collection;

public class InMemoryFriendsRepositoryShould {

    private ExtendedInMemoryFriendsRepository friendsRepository;

    @Before
    public void beforeEachTest() {
        friendsRepository = new ExtendedInMemoryFriendsRepository();
    }

    @Test
    public void add_friendship_request() {
        FriendshipRequest expectedFriendshipRequest = new FriendshipRequest("jesus", "joana");

        friendsRepository.addFriendshipRequest(expectedFriendshipRequest.getRequester(), expectedFriendshipRequest.getRequested());

        Assertions.assertThat(friendsRepository.getFriendshipRequests().size())
            .isEqualTo(1);
        Assertions.assertThat(expectedFriendshipRequest)
            .isEqualTo(Iterables.firstOf(friendsRepository.getFriendshipRequests()));
    }

    @Test
    public void add_friendship() {
        Friendship expectedFriendship = new Friendship("jesus", "joana");

        friendsRepository.addFriendship(expectedFriendship.getUsername(), expectedFriendship.getFriendUsername());

        Assertions.assertThat(friendsRepository.getFriendships().size())
            .isEqualTo(1);
    }

    @Test
    public void return_if_friendship_request_exists() {
        String requester = "jesus";
        String requested = "joana";
        FriendshipRequest friendshipRequest = new FriendshipRequest(requester, requested);

        friendsRepository.setFriendshipRequest(friendshipRequest);

        Assertions.assertThat(friendsRepository.existsFriendshipRequest(requester, requested)).isTrue();
    }

    @Test
    public void return_if_friendship_exists() {
        String username = "jesus";
        String friendUsername = "joana";
        Friendship friendship = new Friendship(username, friendUsername);

        friendsRepository.setFriendship(friendship);

        Assertions.assertThat(friendsRepository.existsFriendship(username, friendUsername)).isTrue();
    }

    @Test
    public void decline_friendship_request() {
        String requester = "jesus";
        String requested = "joana";
        FriendshipRequest friendshipRequest = new FriendshipRequest(requester, requested);
        friendsRepository.setFriendshipRequest(friendshipRequest);

        friendsRepository.declineFriendshipRequest(requester, requested);

        Assertions.assertThat(Iterables.firstOf(friendsRepository.getFriendshipRequests()).isDeclined()).isTrue();
    }

    @Test
    public void return_friends_list() {
        String username = "jesus";
        String friendUsername = "joana";
        Friendship friendship = new Friendship(username, friendUsername);
        friendsRepository.setFriendship(friendship);

        Collection<String> friends = friendsRepository.getFriends(username);

        Assertions.assertThat(Iterables.firstOf(friends)).isEqualTo("joana");
    }

}


