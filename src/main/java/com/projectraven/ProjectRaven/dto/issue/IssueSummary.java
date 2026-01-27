package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.emums.IssueStatus;
import lombok.Getter;

@Getter
public class IssueSummary {
    private final long id;
    private final String title;
    private final IssueStatus status;
    private final IssuePriority priority;

    public IssueSummary(long id, String title, IssueStatus status, IssuePriority priority) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.priority = priority;
    }
}
