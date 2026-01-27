package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.emums.IssueStatus;
import jakarta.validation.constraints.Size;

public record IssueUpdateRequest (
    @Size(min = 5, max = 100)
    String title,

    @Size(max = 1000)
    String description,

    IssuePriority priority,
    IssueStatus status
){}