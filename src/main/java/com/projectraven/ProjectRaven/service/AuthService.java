package com.projectraven.ProjectRaven.service;

import com.projectraven.ProjectRaven.dto.auth.AuthResponse;
import com.projectraven.ProjectRaven.dto.auth.LoginRequest;
import com.projectraven.ProjectRaven.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    void logout(Long actorId);
}
