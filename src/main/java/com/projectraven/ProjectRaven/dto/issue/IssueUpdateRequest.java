package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.emums.IssueStatus;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class IssueUpdateRequest {
    @Size(min = 5, max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    private IssuePriority priority;
    private IssueStatus status;
}
