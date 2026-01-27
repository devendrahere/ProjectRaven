package com.projectraven.ProjectRaven.mapper;

import com.projectraven.ProjectRaven.dto.issue.IssueResponse;
import com.projectraven.ProjectRaven.dto.issue.IssueSummary;
import com.projectraven.ProjectRaven.entity.Issue;

public class IssueMapper {
    private IssueMapper(){

    }
    public static IssueResponse toResponse(Issue issue){
        return new IssueResponse(
                issue.getId(),
                issue.getTitle(),
                issue.getDescription(),
                issue.getStatus(),
                issue.getPriority(),
                ProjectMapper.toSummary(issue.getProject()),
                UserMapper.toSummary(issue.getCreatedBy()),
                issue.getAssignee()==null?null:UserMapper.toSummary(issue.getAssignee()),
                issue.getCreatedAt().toEpochMilli(),
                issue.getUpdatedAt().toEpochMilli()
        );
    }

    public static IssueSummary toSummary(Issue issue){
        return new IssueSummary(
                issue.getId(),
                issue.getTitle(),
                issue.getStatus(),
                issue.getPriority()
        );
    }
}
