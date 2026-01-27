package com.projectraven.ProjectRaven.service.impl;

import com.projectraven.ProjectRaven.dto.user.UserResponse;
import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.dto.user.UserUpdateRequest;
import com.projectraven.ProjectRaven.entity.User;
import com.projectraven.ProjectRaven.mapper.UserMapper;
import com.projectraven.ProjectRaven.repository.UserRepository;
import com.projectraven.ProjectRaven.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getMyProfile(Long actorId) {
        User user= userRepository.findById(actorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserById(Long userId, Long actorId) {
        User user= userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public List<UserSummary> searchUsers(String query, Long actorId) {
        return userRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(UserMapper::toSummary)
                .toList();
    }

    @Override
    public UserResponse updateMyProfile(Long actorId, UserUpdateRequest updatedProfile) {
        User user= userRepository.findById(actorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updatedProfile.username());
        userRepository.save(user);

        return UserMapper.toResponse(user);
    }
}
