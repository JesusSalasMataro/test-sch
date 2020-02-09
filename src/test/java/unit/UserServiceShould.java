package unit;

import com.schibsted.spain.friends.application.UserService;
import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.application.exceptions.UserAlreadyExistsException;
import com.schibsted.spain.friends.application.repositoryInterfaces.UserRepository;
import com.schibsted.spain.friends.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserServiceShould {

    private static UserRepository userRepository;
    private static UserService sut;

    @Before
    public void numOnceBeforeEachTest() {
        userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).save(isA(User.class));
        sut = new UserService(userRepository);
    }

    @Test(expected = InvalidUsernameException.class)
    public void throw_exception_if_username_have_non_alphanumeric_characters() throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        String username = "jesus_";
        String password = "abc";
        sut.registerNewUser(username, password);
    }

    @Test(expected = InvalidUsernameException.class)
    public void throw_exception_if_username_doesnt_have_between_five_and_ten_characters() throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        String username = "jesussalasjuarez";
        String password = "abc";
        sut.registerNewUser(username, password);
    }

    @Test(expected = InvalidPasswordException.class)
    public void throw_exception_if_password_have_non_alphanumeric_characters() throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        String username = "jesus";
        String password = "abcdefgh_";
        sut.registerNewUser(username, password);
    }

    @Test(expected = InvalidPasswordException.class)
    public void throw_exception_if_password_doesnt_have_between_eight_and_twelve_characters() throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        String username = "jesus";
        String password = "abc";
        sut.registerNewUser(username, password);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void throw_exception_if_signup_user_already_exists() throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        String username = "jesus";
        String password = "abcdefgh";
        when(userRepository.exists(isA(String.class))).thenReturn(true);
        sut.registerNewUser(username, password);
    }
}
