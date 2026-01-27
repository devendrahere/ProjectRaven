package com.projectraven.ProjectRaven.dto.user;

import lombok.Getter;

@Getter
public class UserResponse {
    private final Long id;
    private final String username;
    private final String email;
    private final Long createdAt;
    private final Long updatedAt;

    public UserResponse(Long id, String username, String email, Long createdAt, Long updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
