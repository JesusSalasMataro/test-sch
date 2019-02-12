package helpers;

import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.repository.DbUserDto;
import com.schibsted.spain.friends.repository.InMemoryUsersRepository;

import java.util.Collection;

public class ExtendedInMemoryUsersRepository extends InMemoryUsersRepository {

    public Collection<DbUserDto> getUsers() {
        return this.users;
    }

    public void setUser(User user) {
        DbUserDto userDto = new DbUserDto(user.getUsername(), user.getPassword());
        this.users.add(userDto);
    }
}
