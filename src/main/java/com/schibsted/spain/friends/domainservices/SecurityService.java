package com.schibsted.spain.friends.domainservices;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityService {
    public String encript(String password) {
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
}
