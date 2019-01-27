import com.schibsted.spain.friends.application.UserRepository;
import com.schibsted.spain.friends.application.UserService;
import com.schibsted.spain.friends.domain.User;
import com.schibsted.spain.friends.domainservices.FieldValidatorService;
import com.schibsted.spain.friends.domainservices.SecurityService;
import com.schibsted.spain.friends.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.exceptions.InvalidUsernameException;

import com.schibsted.spain.friends.exceptions.UserAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;


public class UserServiceShould {

    private static FieldValidatorService validatorService;
    private static SecurityService securityService;
    private static UserRepository userRepository;
    private static UserService sut;

    @Before
    public void numOnceBeforeEachTest() {
        validatorService = new FieldValidatorService();
        securityService = mock(SecurityService.class);
        when(securityService.encript(isA(String.class))).thenReturn("aabbccddeeff");
        userRepository = mock(UserRepository.class);
        doNothing().when(userRepository).save(isA(User.class));
        sut = new UserService(validatorService, securityService, userRepository);
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

    @Test
    public void call_encript_after_validations() throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        validatorService = mock(FieldValidatorService.class);
        sut = new UserService(validatorService, securityService, userRepository);

        sut.registerNewUser("jesus", "abcdefgh");

        InOrder inOrder = inOrder(validatorService, securityService);
        inOrder.verify(validatorService).validateUsername(isA(String.class));
        inOrder.verify(validatorService).validatePassword(isA(String.class));
        inOrder.verify(securityService).encript(isA(String.class));
    }
}
