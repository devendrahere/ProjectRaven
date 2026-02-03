package com.projectraven.ProjectRaven.controller;

import com.projectraven.ProjectRaven.dto.project.ProjectCreateRequest;
import com.projectraven.ProjectRaven.dto.project.ProjectSummary;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.emums.ProjectRole;
import com.projectraven.ProjectRaven.security.CustomUserDetails;
import com.projectraven.ProjectRaven.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectSummary createProject(
            @Valid
            @RequestBody ProjectCreateRequest request
            ,Authentication authentication) {
        Long actorId=((CustomUserDetails) authentication.getPrincipal()).getId();
        return projectService.createProject(request, actorId);
    }

    @GetMapping("/{projectId}")
    public ProjectSummary getProjectById(
            @PathVariable Long projectId,
            Authentication authentication
    ){
        Long actorId=((CustomUserDetails) authentication.getPrincipal()).getId();
        return projectService.getProjectById(projectId, actorId);
    }

    @GetMapping("/{projectId}/members")
    public List<UserSummary> getProjectMembers(
            @PathVariable Long projectId,
            Authentication authentication
    ){
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        return projectService.getProjectMembers(projectId, actorId);
    }

    @GetMapping("/my")
    public List<ProjectSummary> getMyProjects(
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        return projectService.getMyProjects(actorId);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(
            @PathVariable Long projectId,
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        projectService.deleteProject(projectId, actorId);
    }

    @PostMapping("/{projectId}/members")
    public void addProjectMember(
            @PathVariable Long projectId,
            @RequestParam Long userId,
            Authentication authentication,
            @RequestParam ProjectRole role
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        projectService.addProjectMember(projectId, actorId, userId, role);
    }

    @PutMapping("/{projectId}/members/{userId}")
    public void updateProjectMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long userId,
            @RequestParam ProjectRole role,
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        projectService.changeProjectMemberRole(projectId, actorId, userId, role);
    }

    @DeleteMapping("/{projectId}/member/{userId}")
    public void removeProjectMember(
            @PathVariable Long projectId,
            @PathVariable Long userId,
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        projectService.removeProjectMember(projectId, actorId, userId);
    }
}