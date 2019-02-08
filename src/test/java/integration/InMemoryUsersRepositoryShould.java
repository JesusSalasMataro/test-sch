package integration;

import com.schibsted.spain.friends.domain.Password;
import com.schibsted.spain.friends.domain.User;
import helpers.ExtendedInMemoryUsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Iterables;

public class InMemoryUsersRepositoryShould {

    ExtendedInMemoryUsersRepository usersRepository;

    @Before
    public void beforeEachTest() {
        usersRepository = new ExtendedInMemoryUsersRepository();
    }

    @Test
    public void should_save_user() {
        User user = new User("jesus", new Password("abcde"));

        usersRepository.save(user);

        Assertions.assertThat(user).isEqualTo(Iterables.firstOf(usersRepository.getUsers()));
    }

    @Test
    public void should_return_if_user_is_valid() {
        User user = new User("jesus", new Password("abcde"));
        usersRepository.setUser(user);

        Assertions.assertThat(usersRepository.isValid(user)).isTrue();
    }

    @Test
    public void should_return_if_user_exists() {
        User user = new User("jesus", new Password("abcde"));
        usersRepository.setUser(user);

        Assertions.assertThat(usersRepository.exists(user.getUsername())).isTrue();
    }

}
