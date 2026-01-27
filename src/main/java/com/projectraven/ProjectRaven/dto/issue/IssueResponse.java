package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.dto.project.ProjectSummary;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.emums.IssueStatus;

public record IssueResponse (
    long id,
    String title,
    String description,
    IssueStatus status,
    IssuePriority priority,

    ProjectSummary project,
    UserSummary createdBy,
    UserSummary assignee,

    long createdAt,
    long updatedAt
){}
