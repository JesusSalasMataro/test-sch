package helpers;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.repository.BdUserDto;
import com.schibsted.spain.friends.repository.InMemoryUsersRepository;

import java.util.Collection;

public class ExtendedInMemoryUsersRepository extends InMemoryUsersRepository {

    public Collection<BdUserDto> getUsers() {
        return this.users;
    }

    public void setUser(User user) {
        BdUserDto userDto = new BdUserDto(user.getUsername(), user.getPassword());
        this.users.add(userDto);
    }
}
