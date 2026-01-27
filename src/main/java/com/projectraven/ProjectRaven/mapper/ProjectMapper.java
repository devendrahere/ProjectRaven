package com.projectraven.ProjectRaven.mapper;

import com.projectraven.ProjectRaven.dto.project.ProjectResponse;
import com.projectraven.ProjectRaven.dto.project.ProjectSummary;
import com.projectraven.ProjectRaven.entity.Projects;
import com.projectraven.ProjectRaven.utils.TimeMapper;

public class ProjectMapper {
    private ProjectMapper(){

    }
    public static ProjectResponse toResponse(Projects projects){

        return new ProjectResponse(
                projects.getId(),
                projects.getName(),
                projects.getDescription(),
                TimeMapper.toEpochMillis(projects.getCreatedAt()),
                TimeMapper.toEpochMillis(projects.getUpdatedAt())
        );
    }

    public static ProjectSummary toSummary(Projects projects){

        return new ProjectSummary(
                projects.getId(),
                projects.getName()
        );
    }
}
