package com.projectraven.ProjectRaven.dto.project;

import lombok.Getter;

@Getter
public class ProjectResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Long createdAt;
    private final Long updatedAt;

    public ProjectResponse(Long id, String name, String description, Long createdAt, Long updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
