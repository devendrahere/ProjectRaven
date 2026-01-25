package com.projectraven.ProjectRaven.repository;

import com.projectraven.ProjectRaven.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
}