package com.projectraven.ProjectRaven.controller;

import com.projectraven.ProjectRaven.dto.issue.IssueCreateRequest;
import com.projectraven.ProjectRaven.dto.issue.IssueResponse;
import com.projectraven.ProjectRaven.dto.issue.IssueSummary;
import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.security.CustomUserDetails;
import com.projectraven.ProjectRaven.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @PostMapping("/projects/{projectId}/issues")
    @ResponseStatus(HttpStatus.CREATED)
    public IssueResponse createIssue(
            @RequestParam Long projectId,
            @RequestBody IssueCreateRequest request,
            Authentication authentication
    ) {
        Long actorId =((CustomUserDetails) authentication.getPrincipal()).getId();
        return issueService.createIssue(projectId, request, actorId);
    }

    @GetMapping("/issues/{issueId}")
    public IssueResponse getIssueById(
            @PathVariable Long issueId,
            Authentication authentication
    ){
        Long actorId =((CustomUserDetails) authentication.getPrincipal()).getId();
        return issueService.getIssueById(issueId, actorId);
    }

    @GetMapping("/project/{projectId}/issues")
    public List<IssueSummary> getIssuesForProject(
            @PathVariable Long projectId,
            Authentication authentication
    ){
        Long actorId =((CustomUserDetails) authentication.getPrincipal()).getId();
        return issueService.getIssuesForProject(projectId, actorId);
    }

    @PostMapping("/issues/{issueId}/assignee")
    public void assignIssue(
            @PathVariable Long issueId,
            @RequestParam Long assignToUserId,
            Authentication authentication
    ){
        Long actorId =((CustomUserDetails) authentication.getPrincipal()).getId();
        issueService.assignIssue(issueId, assignToUserId, actorId);
    }

    @DeleteMapping("/issues/{issueId}/assignee")
    public void unassignIssue(
            @PathVariable Long issueId,
            Authentication authentication
    ){
        Long actorId =((CustomUserDetails) authentication.getPrincipal()).getId();
        issueService.unassignIssue(issueId, actorId);
    }

    @PostMapping("/issues/{issueId}/priority")
    public void changeIssuePriority(
            @PathVariable Long issueId,
            @RequestParam IssuePriority newPriority,
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        issueService.changeIssuePriority(issueId, newPriority, actorId);
    }

    @DeleteMapping("/issues/{issueId}")
    public void deleteIssue(
            @PathVariable Long issueId,
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        issueService.deleteIssue(issueId, actorId);
    }
}