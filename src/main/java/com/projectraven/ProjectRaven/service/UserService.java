package com.projectraven.ProjectRaven.service;

import com.projectraven.ProjectRaven.dto.user.UserResponse;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.dto.user.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse getMyProfile(Long actorId);

    UserResponse getUserById(
            Long userId,
            Long actorId
    );

    List<UserSummary> searchUsers(String query, Long actorId);

    UserResponse updateMyProfile(
            Long actorId,
            UserUpdateRequest updatedProfile
    );
}