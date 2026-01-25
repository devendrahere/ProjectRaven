package com.projectraven.ProjectRaven.emums;

public enum ProjectRole {
    ADMIN,
    // has full access to all project features
    DEVELOPER,
    // can create and manage issues, but cannot change project settings
    VIEWER,
    // can only view project details and issues
}
