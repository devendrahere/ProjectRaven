package com.projectraven.ProjectRaven.service;

import com.projectraven.ProjectRaven.dto.issue.IssueCreateRequest;
import com.projectraven.ProjectRaven.dto.issue.IssueResponse;
import com.projectraven.ProjectRaven.dto.issue.IssueSummary;
import com.projectraven.ProjectRaven.emums.IssuePriority;

import java.util.List;

public interface IssueService {
    IssueResponse createIssue(Long projectId, IssueCreateRequest request, Long actorId);

    IssueResponse getIssueById(Long issueId, Long actorId);

    List<IssueSummary> getIssuesForProject(Long projectId, Long actorId);

    void assignIssue(Long issueId,Long assignToUserId,Long actorId);

    void unassignIssue(Long issueId,Long actorId);

    void changeIssuePriority(Long issueId, IssuePriority newPriority, Long actorId);

    void deleteIssue(Long issueId, Long actorId);
}
