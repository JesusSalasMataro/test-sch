package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.domain.Password;

import java.util.Objects;

public class BdUserDto {

    private String username;
    private Password password;

    public BdUserDto(String username, Password password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BdUserDto bdUserDto = (BdUserDto) o;
        return Objects.equals(username, bdUserDto.username) &&
                Objects.equals(password, bdUserDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

}
