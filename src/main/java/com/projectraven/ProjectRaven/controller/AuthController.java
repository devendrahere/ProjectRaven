package com.projectraven.ProjectRaven.controller;

import com.projectraven.ProjectRaven.dto.auth.AuthResponse;
import com.projectraven.ProjectRaven.dto.auth.LoginRequest;
import com.projectraven.ProjectRaven.dto.auth.RegisterRequest;
import com.projectraven.ProjectRaven.security.CustomUserDetails;
import com.projectraven.ProjectRaven.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(
            @RequestBody RegisterRequest request
            ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid
            @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
    //refresh token are needed to be implemented for logout to work properly
    @PostMapping("/logout")
    public void logout(Authentication authentication) {
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();
        authService.logout(userId);
    }
}