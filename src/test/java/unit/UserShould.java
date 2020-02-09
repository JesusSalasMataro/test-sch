package unit;

import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.domain.User;
import org.junit.Test;

public class UserShould {

  @Test
  public void create_when_username_and_password_are_valid() throws InvalidUsernameException, InvalidPasswordException {
    String aValidUsername = "beatriz";
    String aValidPassword = "qwerty12345";

    User user = new User(aValidUsername, aValidPassword);
  }
}
