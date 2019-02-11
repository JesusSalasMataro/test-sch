package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.application.UserService;
import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;
import com.schibsted.spain.friends.application.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupLegacyController {

  @Autowired
  private UserService userService;

  @PostMapping
  ResponseEntity signUp(
      @RequestParam("username") String username,
      @RequestHeader("X-Password") String password
  ) {
    try {
      userService.registerNewUser(username, password);
    }
    catch(InvalidUsernameException invalidUsernameException){
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    catch(InvalidPasswordException invalidPasswordException) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    catch(UserAlreadyExistsException userAlreadyExistsException) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity(HttpStatus.CREATED);
  }
}
