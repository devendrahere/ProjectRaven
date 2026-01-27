package com.projectraven.ProjectRaven.controller;

import com.projectraven.ProjectRaven.dto.user.UserResponse;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.dto.user.UserUpdateRequest;
import com.projectraven.ProjectRaven.security.CustomUserDetails;
import com.projectraven.ProjectRaven.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication  authentication) {
        Long userId=((CustomUserDetails) authentication.getPrincipal()).getId();
        return userService.getMyProfile(userId);
    }

    @GetMapping("/{userID}")
    public UserResponse getUserProfile(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        return userService.getUserById(userId, actorId);
    }

    @GetMapping("/search")
    public List<UserSummary> searchUsers(
            @RequestParam String query,
            Authentication authentication
    ) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        return userService.searchUsers(query, actorId);
    }

    @PutMapping("/me")
    public UserResponse updateMyProfile(Authentication authentication,
                                        @Valid
                                        @RequestBody UserUpdateRequest userUpdateRequest) {
        Long actorId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        return userService.updateMyProfile(actorId, userUpdateRequest);
    }
}