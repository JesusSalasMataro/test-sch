package com.schibsted.spain.friends.domain;

import com.schibsted.spain.friends.application.exceptions.InvalidUsernameException;

public class Username {

  private static final int MINIMUM_LENGTH = 5;
  private static final int MAXIMUM_LENGTH = 10;

  private String value;

  public Username(String username) throws InvalidUsernameException {
    checkAllCharactersAreAlphanumeric(username);
    checkValidLength(username);

    this.value = username;
  }

  public String asString() {
    return value;
  }

  private void checkAllCharactersAreAlphanumeric(String username) throws InvalidUsernameException {
    if (!username.matches("[a-zA-Z0-9]+")) {
      throw new InvalidUsernameException("Provided username contains non alphanumeric values");
    }
  }

  private void checkValidLength(String username) throws InvalidUsernameException {
    if (username.length() < MINIMUM_LENGTH || username.length() > MAXIMUM_LENGTH) {
      throw new InvalidUsernameException(
          "Username must be between " + MINIMUM_LENGTH + " and " + MAXIMUM_LENGTH + " characters length.");
    }
  }
}
