package com.projectraven.ProjectRaven.dto.user;


public record UserResponse(
    Long id,
    String username,
    String email,
    Long createdAt,
    Long updatedAt

){}