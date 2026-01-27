package com.projectraven.ProjectRaven.dto.auth;

public record AuthResponse (
    String accessToken,
    String tokenType,
    Long expiresAt
){}