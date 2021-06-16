package com.juanprato.mytouch.model;

import com.juanprato.mytouch.common.constants.FriendAcceptedType;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue()
    @Column(name = "friendship_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", nullable = false, updatable = false, referencedColumnName = "user_id")
    private User friend1;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", nullable = false, updatable = false, referencedColumnName = "user_id")
    private User friend2;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    public Friend() {}

    public Friend(User friend1, User friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    public Friend(User friend1, User friend2, Date created_at) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFriend1() {
        return friend1;
    }

    public void setFriend1(User friend1) {
        this.friend1 = friend1;
    }

    public User getFriend2() {
        return friend2;
    }

    public void setFriend2(User friend2) {
        this.friend2 = friend2;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return Objects.equals(id, friend.id) && Objects.equals(friend1, friend.friend1) && Objects.equals(friend2, friend.friend2) && Objects.equals(created_at, friend.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, friend1, friend2, created_at);
    }
}
