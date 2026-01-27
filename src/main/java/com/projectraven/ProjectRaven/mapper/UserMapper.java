package com.projectraven.ProjectRaven.mapper;

import com.projectraven.ProjectRaven.dto.user.UserResponse;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.utils.TimeMapper;

public class UserMapper {
    private UserMapper(){

    }

    public static UserResponse toResponse(com.projectraven.ProjectRaven.entity.User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                TimeMapper.toEpochMillis(user.getCreatedAt()),
                TimeMapper.toEpochMillis(user.getUpdatedAt())
        );
    }

    public static UserSummary toSummary(com.projectraven.ProjectRaven.entity.User user){
        return new UserSummary(
                user.getId(),
                user.getName()
        );
    }
}
