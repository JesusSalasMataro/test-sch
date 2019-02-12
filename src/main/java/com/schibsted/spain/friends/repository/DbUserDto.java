package com.schibsted.spain.friends.repository;

import com.schibsted.spain.friends.domain.Password;

import java.util.Objects;

public class DbUserDto {

    private String username;
    private Password password;

    public DbUserDto(String username, Password password) {
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
        DbUserDto dbUserDto = (DbUserDto) o;
        return Objects.equals(username, dbUserDto.username) &&
                Objects.equals(password, dbUserDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

}
