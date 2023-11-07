package com.socialnetwork.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository     
    extends JpaRepository<User, Long> {    
    boolean existsUserByEmail(String email);
    boolean existsUserById(Long id);
}