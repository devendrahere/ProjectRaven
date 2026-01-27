package com.projectraven.ProjectRaven.repository;

import com.projectraven.ProjectRaven.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByTypeAndProjectId(String type, Long projectId);

    Optional<ChatRoom> findByTypeAndIssueId(String type, Long issueId);
}
