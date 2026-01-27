package com.projectraven.ProjectRaven.service;

import com.projectraven.ProjectRaven.dto.project.ProjectCreateRequest;
import com.projectraven.ProjectRaven.dto.project.ProjectSummary;
import com.projectraven.ProjectRaven.emums.ProjectRole;
import com.projectraven.ProjectRaven.entity.Projects;

import java.util.List;

public interface ProjectService {
    ProjectSummary createProject(ProjectCreateRequest request, Long actorId);

    void deleteProject(Long projectId,Long actorId);

    ProjectSummary getProjectById(Long projectId,Long actorId);

    List<ProjectSummary> getMyProjects(Long actorId);

    void  addProjectMember(
            Long projectId,
            Long actorId,
            Long userIdToAdd,
            ProjectRole role
    );

    void changeProjectMemberRole(
            Long projectId,
            Long actorId,
            Long targetUserId,
            ProjectRole newRole
    );

    void removeProjectMember(
            Long projectId,
            Long actorId,
            Long targetUserId
    );
}
