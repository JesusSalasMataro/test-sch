package integration;

import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;
import com.schibsted.spain.friends.repository.DbUserDto;
import helpers.ExtendedInMemoryUsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.util.collections.Iterables;

public class InMemoryUsersRepositoryShould {

    private ExtendedInMemoryUsersRepository usersRepository;
    private static FieldValidatorService validatorService;

    @BeforeClass
    public static void beforeAllTests() {
        validatorService = new FieldValidatorService();
    }

    @Before
    public void beforeEachTest() {
        usersRepository = new ExtendedInMemoryUsersRepository();
    }


    @Test
    public void should_save_user() throws InvalidPasswordException, InvalidUsernameException {
        User user = new User("jesus", "abcdefghi", validatorService);
        DbUserDto userDto = new DbUserDto(user.getUsername(), user.getPassword());

        usersRepository.save(user);

        Assertions.assertThat(userDto).isEqualTo(Iterables.firstOf(usersRepository.getUsers()));
    }

    @Test
    public void should_return_if_user_is_valid() throws InvalidPasswordException, InvalidUsernameException {
        User user = new User("jesus", "abcdefghi", validatorService);
        usersRepository.setUser(user);

        Assertions.assertThat(usersRepository.isValid(user)).isTrue();
    }

    @Test
    public void should_return_if_user_exists() throws InvalidPasswordException, InvalidUsernameException {
        User user = new User("jesus", "abcdefghi", validatorService);
        usersRepository.setUser(user);

        Assertions.assertThat(usersRepository.exists(user.getUsername())).isTrue();
    }

}
