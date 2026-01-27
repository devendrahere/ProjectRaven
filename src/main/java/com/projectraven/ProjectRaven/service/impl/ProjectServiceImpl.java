package com.projectraven.ProjectRaven.service.impl;

import com.projectraven.ProjectRaven.dto.project.ProjectCreateRequest;
import com.projectraven.ProjectRaven.dto.project.ProjectSummary;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.emums.ProjectRole;
import com.projectraven.ProjectRaven.entity.ProjectMember;
import com.projectraven.ProjectRaven.entity.Projects;
import com.projectraven.ProjectRaven.entity.User;
import com.projectraven.ProjectRaven.mapper.ProjectMapper;
import com.projectraven.ProjectRaven.mapper.ProjectMemberMapper;
import com.projectraven.ProjectRaven.repository.ProjectMemberRepository;
import com.projectraven.ProjectRaven.repository.ProjectRepository;
import com.projectraven.ProjectRaven.repository.UserRepository;
import com.projectraven.ProjectRaven.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectSummary createProject(ProjectCreateRequest request, Long actorId) {
        User owner = userRepository.findById(actorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Projects project = new Projects();
        project.setName(request.name());
        project.setDescription(request.description());

        project.setCreatedBy(owner);
        projectRepository.save(project);

        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(owner);
        projectMember.setProjectRole(ProjectRole.OWNER);
        projectMemberRepository.save(projectMember);
        return ProjectMapper.toSummary(project);
    }

    @Override
    public void deleteProject(Long projectId, Long actorId) {
        requireProjectOwner(projectId, actorId);
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectSummary getProjectById(Long projectId, Long actorId) {
        requiredProjectMember(projectId, actorId);
        Projects projects= projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return ProjectMapper.toSummary(projects);
    }

    @Override
    public List<ProjectSummary> getMyProjects(Long actorId) {
        return projectRepository.findProjectsForUser(actorId)
                .stream()
                .map(ProjectMapper::toSummary)
                .toList();
    }

    @Override
    public void addProjectMember(Long projectId, Long actorId, Long userIdToAdd, ProjectRole role) {
        User userToAdd= userRepository.findById(userIdToAdd)
                .orElseThrow(() -> new RuntimeException("User to add not found"));
        requireProjectOwnerOrManager( projectId, actorId);

        boolean isMemberExists= projectMemberRepository.existsByProjectIdAndUserId(projectId, userIdToAdd);
        if (isMemberExists) {
            throw new RuntimeException("User is already a member of the project");
        }
        Projects project= projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        ProjectMember projectMember= new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(userToAdd);
        projectMember.setProjectRole(role);
        projectMemberRepository.save(projectMember);
    }

    @Override
    public List<UserSummary> getProjectMembers(Long projectId, Long actorId) {
        requiredProjectMember(projectId, actorId);
        return projectMemberRepository.findByProjectId(projectId)
                .stream()
                .map(ProjectMemberMapper::toUserSummary)
                .toList();
    }

    @Override
    public void changeProjectMemberRole(Long projectId, Long actorId, Long targetUserId, ProjectRole newRole) {
        requireProjectOwner( projectId, actorId);
        ProjectMember projectMember= projectMemberRepository.findByProjectIdAndUserId(projectId, targetUserId)
                .orElseThrow(() -> new RuntimeException("Target user is not a member of the project"));
        projectMember.setProjectRole(newRole);
        projectMemberRepository.save(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long actorId, Long targetUserId) {
       ProjectMember actor= projectMemberRepository.findByProjectIdAndUserId(projectId, actorId)
                .orElseThrow(() -> new RuntimeException("Actor is not a member of the project"));

       ProjectMember targetMember= projectMemberRepository.findByProjectIdAndUserId(projectId, targetUserId)
                .orElseThrow(() -> new RuntimeException("Target user is not a member of the project"));
         if (actor.getProjectRole() == ProjectRole.OWNER ){
             if(targetMember.getProjectRole()== ProjectRole.OWNER){
                 throw new RuntimeException("Owner cannot remove themselves");
             }
             projectMemberRepository.delete(targetMember);
         }
         else if (actor.getProjectRole() == ProjectRole.MANAGER) {
             if (targetMember.getProjectRole() == ProjectRole.OWNER || targetMember.getProjectRole() == ProjectRole.MANAGER) {
                 throw new RuntimeException("Manager cannot remove owner or another manager");
             }
             projectMemberRepository.delete(targetMember);
         }
         else {
             throw new RuntimeException("User does not have permission to remove members");
         }
    }

    private void requireProjectOwner(Long projectId, Long userId) {
        ProjectMember projectMember =projectMemberRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new RuntimeException("User is not a member of the project"));
        if (projectMember.getProjectRole() != ProjectRole.OWNER) {
            throw new RuntimeException("User is not the owner of the project");
        }
    }

    private void requireProjectOwnerOrManager(Long projectId, Long userId) {
        ProjectMember projectMember=projectMemberRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new RuntimeException("User is not a member of the project"));
        if (projectMember.getProjectRole() != ProjectRole.OWNER && projectMember.getProjectRole() != ProjectRole.MANAGER) {
            throw new RuntimeException("User is not the owner or admin of the project");
        }
    }

    private void requiredProjectMember(Long projectId, Long userId) {
        projectMemberRepository.findByProjectIdAndUserId(projectId, userId)
                .orElseThrow(() -> new RuntimeException("User is not a member of the project"));
    }
}
