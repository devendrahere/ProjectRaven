package com.projectraven.ProjectRaven.repository;

import com.projectraven.ProjectRaven.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    List<ChatMessage> findByChatRoomIdAndDeletedFalseOrderByCreatedAtAsc(Long chatRoomId);
}