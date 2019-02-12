package helpers;

import com.schibsted.spain.friends.repository.Friendship;
import com.schibsted.spain.friends.repository.FriendshipRequest;
import com.schibsted.spain.friends.repository.InMemoryFriendshipRepository;

import java.util.Collection;

public class ExtendedInMemoryFriendsRepository extends InMemoryFriendshipRepository {

    public Collection<FriendshipRequest> getFriendshipRequests() {
        return this.friendshipsRequests;
    }

    public Collection<Friendship> getFriendships() {
        return this.friendships;
    }

    public void setFriendshipRequest (FriendshipRequest friendshipRequest) {
        this.friendshipsRequests.add(friendshipRequest);
    }

    public void setFriendship (Friendship friendship) {
        this.friendships.add(friendship);
    }
}
