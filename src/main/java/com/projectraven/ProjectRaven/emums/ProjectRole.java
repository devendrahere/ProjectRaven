package com.projectraven.ProjectRaven.emums;

public enum ProjectRole {
    OWNER,
    //creator of the project with all permissions
    MANAGER,
    //can manage project settings and members
    DEVELOPER,
    //can work on issues and tasks
    VIEWER
    //read-only access to the project
}
