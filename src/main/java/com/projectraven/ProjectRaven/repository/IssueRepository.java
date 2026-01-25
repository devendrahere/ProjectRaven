package com.projectraven.ProjectRaven.repository;

import com.projectraven.ProjectRaven.emums.IssueStatus;
import com.projectraven.ProjectRaven.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<Issue> findByIdAndDeletedFalse(Long id);

    List<Issue> findByProjectIdAndDeletedFalse(Long projectId);

    List<Issue> findByProjectAndStatusAndDeletedFalse(Long projectId, IssueStatus status);

    List<Issue> findByAssigneeIdAndDeletedFalse(Long assigneeId);
}
