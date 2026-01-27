package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.emums.IssueStatus;
import lombok.Getter;


public record IssueSummary (
    long id,
    String title,
    IssueStatus status,
    IssuePriority priority
){}
