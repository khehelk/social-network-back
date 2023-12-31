package com.socialnetwork.repository;

import com.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository     
    extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);
}