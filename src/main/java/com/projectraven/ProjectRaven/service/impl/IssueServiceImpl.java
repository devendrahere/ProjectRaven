package com.projectraven.ProjectRaven.service.impl;

import com.projectraven.ProjectRaven.dto.issue.IssueCreateRequest;
import com.projectraven.ProjectRaven.dto.issue.IssueResponse;
import com.projectraven.ProjectRaven.dto.issue.IssueSummary;
import com.projectraven.ProjectRaven.emums.IssuePriority;
import com.projectraven.ProjectRaven.emums.ProjectRole;
import com.projectraven.ProjectRaven.entity.Issue;
import com.projectraven.ProjectRaven.entity.ProjectMember;
import com.projectraven.ProjectRaven.entity.Projects;
import com.projectraven.ProjectRaven.entity.User;
import com.projectraven.ProjectRaven.mapper.IssueMapper;
import com.projectraven.ProjectRaven.repository.IssueRepository;
import com.projectraven.ProjectRaven.repository.ProjectMemberRepository;
import com.projectraven.ProjectRaven.repository.ProjectRepository;
import com.projectraven.ProjectRaven.repository.UserRepository;
import com.projectraven.ProjectRaven.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;

    @Override
    public IssueResponse createIssue(Long projectId, IssueCreateRequest request, Long actorId) {
        User creator= userRepository.findById(actorId)
                .orElseThrow(()->new RuntimeException("User not found"));
        Projects projects= projectRepository.findById(projectId)
                .orElseThrow(()->new RuntimeException("Project not found"));
        Issue issue=new Issue();
        issue.setTitle(request.title());
        issue.setDescription(request.description());
        issue.setCreatedBy(creator);
        issue.setProject(projects);
        issue.setPriority(request.priority());
        issueRepository.save(issue);

        return IssueMapper.toResponse(issue);
    }

    @Override
    public IssueResponse getIssueById(Long issueId, Long actorId) {
        Issue issue=issueRepository.findById(issueId)
                .orElseThrow(()->new RuntimeException("Issue not found"));
        requiredProjectMember(issue.getProject().getId(),actorId);
        return IssueMapper.toResponse(
                issueRepository.findById(issueId)
                        .orElseThrow(()->new RuntimeException("Issue not found"))
        );
    }

    @Override
    public List<IssueSummary> getIssuesForProject(Long projectId, Long actorId) {
        requiredProjectMember(projectId,actorId);
        return issueRepository.findByProjectIdAndDeletedFalse(projectId)
                .stream()
                .map(IssueMapper::toSummary)
                .collect(toList());
    }

    @Override
    public void assignIssue(Long issueId, Long assignToUserId, Long actorId) {
        Issue issue=issueRepository.findById(issueId)
                .orElseThrow(()->new RuntimeException("Issue not found"));
        Projects project=issue.getProject();

        requireProjectOwnerOrManager(project.getId(), actorId);
        ProjectMember assigneeMember=projectMemberRepository.findByProjectIdAndUserId(project.getId(), assignToUserId)
                .orElseThrow(()->new RuntimeException("Assignee is not a member of the project"));
        issue.setAssignee(assigneeMember.getUser());
        issueRepository.save(issue);
    }

    @Override
    public void unassignIssue(Long issueId, Long actorId) {
        Projects project=issueRepository.findById(issueId)
                .orElseThrow(()->new RuntimeException("Issue not found"))
                .getProject();

        requireProjectOwnerOrManager(project.getId(), actorId);

        Issue issue=issueRepository.findById(issueId)
                .orElseThrow(()->new RuntimeException("Issue not found"));

        issue.setAssignee(null);
        issueRepository.save(issue);
    }

    @Override
    public void changeIssuePriority(Long issueId, IssuePriority newPriority, Long actorId) {
        Issue issue=issueRepository.findById(issueId)
                .orElseThrow(()->new RuntimeException("Issue not found"));
        requireProjectOwnerOrManager(issue.getProject().getId(), actorId);
        issue.setPriority(newPriority);
        issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId, Long actorId) {
        User actor= userRepository.findById(actorId)
                .orElseThrow(()->new RuntimeException("User not found"));
        Issue issue=issueRepository.findById(issueId)
                .orElseThrow(()->new RuntimeException("Issue not found"));
        requiredOwner(issue.getProject().getId(), actorId);
        issue.setDeleted(true);
        issueRepository.save(issue);
    }

    private void requiredOwner(Long projectId, Long actorId) {
        ProjectMember projectMember=projectMemberRepository.findByProjectIdAndUserId(projectId,actorId)
                .orElseThrow(()->new RuntimeException("Project member not found"));
        if (projectMember.getProjectRole() != ProjectRole.OWNER){
            throw new RuntimeException("Only project owner can perform this action");
        }
    }

    private void requireProjectOwnerOrManager(Long projectId, Long actorId) {
        ProjectMember projectMember=projectMemberRepository.findByProjectIdAndUserId(projectId,actorId)
                .orElseThrow(()->new RuntimeException("Project member not found"));
        if (projectMember.getProjectRole() != ProjectRole.OWNER &&
                projectMember.getProjectRole() != ProjectRole.MANAGER){
            throw new RuntimeException("Only project owner or manager can perform this action");
        }
    }


    private void requiredProjectMember(Long projectId, Long actorId) {
        projectMemberRepository.findByProjectIdAndUserId(projectId,actorId)
                .orElseThrow(()->new RuntimeException("Project member not found"));
    }
}