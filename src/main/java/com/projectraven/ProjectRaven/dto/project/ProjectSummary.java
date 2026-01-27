package com.projectraven.ProjectRaven.dto.project;

import lombok.Getter;

@Getter
public class ProjectSummary {
    private final Long id;
    private final String name;

    public ProjectSummary(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
