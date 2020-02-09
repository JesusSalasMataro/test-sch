package com.schibsted.spain.friends.domain;

import com.schibsted.spain.friends.application.exceptions.InvalidPasswordException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Password {
    private static final int MINIMUM_LENGTH = 8;
    private static final int MAXIMUM_LENGTH = 12;

    private String encriptedPassword;

    public Password (String password) throws InvalidPasswordException {
        checkAllCharactersAreAlphanumeric(password);
        checkValidLength(password);

        this.encriptedPassword = hash(password);
    }

    public String getEncriptedPassword() {
        return this.encriptedPassword;
    }

    private String hash(String password) {
        String hashedPassword;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashedPassword = no.toString(16);

            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return hashedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(encriptedPassword, password.getEncriptedPassword());
    }

    private void checkAllCharactersAreAlphanumeric(String password) throws InvalidPasswordException {
        if (!password.matches("[a-zA-Z0-9]+")) {
            throw new InvalidPasswordException("Provided password contains non alphanumeric values");
        }
    }

    private void checkValidLength(String password) throws InvalidPasswordException {
        if (password.length() < MINIMUM_LENGTH || password.length() > MAXIMUM_LENGTH) {
            throw new InvalidPasswordException(
                "Password must be between " + MINIMUM_LENGTH + " and " + MAXIMUM_LENGTH + " characters length.");
        }
    }
}
