package com.juanprato.mytouch.repository;

import com.juanprato.mytouch.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Boolean existsByFriend1AndFriend2(Long friend1, Long friend2);

}
