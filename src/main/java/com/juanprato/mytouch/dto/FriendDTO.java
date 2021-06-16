package com.juanprato.mytouch.dto;

import java.util.Date;

public class FriendDTO {

    private UserWithoutFriendsDTO friend1;
    private UserWithoutFriendsDTO friend2;
    private Date created_at;

    public FriendDTO() {
    }

    public UserWithoutFriendsDTO getFriend1() {
        return friend1;
    }

    public void setFriend1(UserWithoutFriendsDTO friend1) {
        this.friend1 = friend1;
    }

    public UserWithoutFriendsDTO getFriend2() {
        return friend2;
    }

    public void setFriend2(UserWithoutFriendsDTO friend2) {
        this.friend2 = friend2;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
