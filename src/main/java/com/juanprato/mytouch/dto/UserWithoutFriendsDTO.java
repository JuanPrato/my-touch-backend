package com.juanprato.mytouch.dto;

public class UserWithoutFriendsDTO {

    private String username;
    private String profileImage;
    private String email;
    private String name;
    private String lastname;

    public UserWithoutFriendsDTO() {
    }

    public UserWithoutFriendsDTO(String username, String profileImage, String email, String name, String lastname) {
        this.username = username;
        this.profileImage = profileImage;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
