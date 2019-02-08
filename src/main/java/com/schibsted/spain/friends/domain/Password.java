package com.schibsted.spain.friends.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Password {

    private String encriptedPassword;

    public Password (String password) {
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
}
