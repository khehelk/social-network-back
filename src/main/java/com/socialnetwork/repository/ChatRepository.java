package com.socialnetwork.repository;

import com.socialnetwork.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long>{
}
