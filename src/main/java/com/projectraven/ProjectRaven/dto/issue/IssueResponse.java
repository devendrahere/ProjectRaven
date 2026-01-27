package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.dto.project.ProjectSummary;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.emums.IssueStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IssueResponse {
    private final long id;
    private final String title;
    private final String description;
    private final IssueStatus status;
    private final IssuePriority priority;

    private final ProjectSummary project;
    private final UserSummary createdBy;
    private final UserSummary assignee;

    private final long createdAt;
    private final long updatedAt;

}
