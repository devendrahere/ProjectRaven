package com.projectraven.ProjectRaven.repository;

import com.projectraven.ProjectRaven.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByIssueIdAndDeletedFalse(Long issueId);
}
