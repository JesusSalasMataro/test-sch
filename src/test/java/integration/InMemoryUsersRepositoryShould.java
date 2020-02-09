package integration;

import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.domain.User;
import helpers.ExtendedInMemoryUsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Iterables;

public class InMemoryUsersRepositoryShould {

    private ExtendedInMemoryUsersRepository usersRepository;

    @Before
    public void beforeEachTest() {
        usersRepository = new ExtendedInMemoryUsersRepository();
    }

    @Test
    public void should_save_user() throws InvalidPasswordException, InvalidUsernameException {
        User user = new User("jesus", "abcdefghi");
        usersRepository.save(user);

        Assertions.assertThat(user).isEqualTo(Iterables.firstOf(usersRepository.getUsers()));
    }

    @Test
    public void should_return_if_user_with_password_exists() throws InvalidPasswordException, InvalidUsernameException {
        User user = new User("jesus", "abcdefghi");
        usersRepository.setUser(user);

        Assertions.assertThat(usersRepository.exists(user.getUsername().asString(), user.getPassword())).isTrue();
    }

    @Test
    public void should_return_if_user_exists() throws InvalidPasswordException, InvalidUsernameException {
        User user = new User("jesus", "abcdefghi");
        usersRepository.setUser(user);

        Assertions.assertThat(usersRepository.exists(user.getUsername().asString())).isTrue();
    }

}
