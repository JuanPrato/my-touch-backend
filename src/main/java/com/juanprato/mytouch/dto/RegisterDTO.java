package com.juanprato.mytouch.dto;

import com.google.firebase.database.annotations.NotNull;

public class RegisterDTO {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;

    public RegisterDTO() {}

    public RegisterDTO(@NotNull String username,@NotNull String password,
                       @NotNull String name,@NotNull String lastname,@NotNull String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}