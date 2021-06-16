package com.juanprato.mytouch.dto;

import java.util.List;
import java.util.Set;

public class UserDTO {

    private String username;
    private String profileImage;
    private String email;
    private String name;
    private String lastname;
    private Set<FriendDTO> friends;

    public UserDTO() {}

    public UserDTO(String username, String profileImage, String email, String name, String lastname, Set<FriendDTO> friends) {
        this.username = username;
        this.profileImage = profileImage;
        this.email = email;
        this.name = name;
        this.lastname = lastname;
        this.friends = friends;
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

    public Set<FriendDTO> getFriends() {
        return friends;
    }

    public void setFriends(Set<FriendDTO> friends) {
        this.friends = friends;
    }
}
