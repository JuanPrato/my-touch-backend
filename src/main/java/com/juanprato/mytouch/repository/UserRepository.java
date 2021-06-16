package com.juanprato.mytouch.repository;

import com.juanprato.mytouch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);

    Boolean existsByUsername(String username);

}
