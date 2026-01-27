package com.projectraven.ProjectRaven.service.impl;

import com.projectraven.ProjectRaven.dto.auth.AuthResponse;
import com.projectraven.ProjectRaven.dto.auth.LoginRequest;
import com.projectraven.ProjectRaven.dto.auth.RegisterRequest;
import com.projectraven.ProjectRaven.emums.SystemRole;
import com.projectraven.ProjectRaven.entity.User;
import com.projectraven.ProjectRaven.repository.UserRepository;
import com.projectraven.ProjectRaven.security.CustomUserDetails;
import com.projectraven.ProjectRaven.security.jwt.JwtService;
import com.projectraven.ProjectRaven.service.AuthService;
import com.projectraven.ProjectRaven.utils.TimeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already in use");
        }

        if (userRepository.existsByName(request.name())){
            throw new RuntimeException("Username already in use");
        }

        User user= new User();
        user.setEmail(request.email());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setSystemRole(SystemRole.USER);
        userRepository.save(user);

        CustomUserDetails userDetails= new CustomUserDetails(user);
        String token= jwtService.generateToken(userDetails,user.getId());
        return new AuthResponse(
                token,
                "Bearer ",
                TimeMapper.toEpochMillis(jwtService.extractExpiration(token).toInstant())
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user= userRepository.findByEmail(request.email())
                .orElseThrow(()->new RuntimeException("Invalid email or password"));
        if (!passwordEncoder.matches(request.password(),user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
        CustomUserDetails userDetails= new CustomUserDetails(user);
        String token= jwtService.generateToken(userDetails,user.getId());
        return new AuthResponse(
                token,
                "Bearer ",
                TimeMapper.toEpochMillis(jwtService.extractExpiration(token).toInstant())
        );
    }

    @Override
    public void logout(Long actorId) {

    }
}