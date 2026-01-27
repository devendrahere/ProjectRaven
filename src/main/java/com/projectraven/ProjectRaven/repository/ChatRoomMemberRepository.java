package com.projectraven.ProjectRaven.repository;

import com.projectraven.ProjectRaven.entity.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember,Long> {
    boolean existsByChatRoomIdAndUserId(Long chatRoomId, Long userId);

    long countByChatRoomId(Long chatRoomId);
}
