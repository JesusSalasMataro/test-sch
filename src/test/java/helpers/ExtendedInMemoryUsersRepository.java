package helpers;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.repository.InMemoryUsersRepository;

import java.util.Collection;

public class ExtendedInMemoryUsersRepository extends InMemoryUsersRepository {

    public Collection<User> getUsers() {
        return this.users;
    }

    public void setUser(User user) {
        this.users.add(user);
    }
}
