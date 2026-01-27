package com.projectraven.ProjectRaven.repository;

import com.projectraven.ProjectRaven.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    List<Projects> findAllByCreatedBy(Long ownerId);

    @Query("""
    select p
    from Projects p
    join ProjectMember pm on pm.project = p
    where pm.user.id = :userId
""")
    List<Projects> findProjectsForUser(Long userId);

}