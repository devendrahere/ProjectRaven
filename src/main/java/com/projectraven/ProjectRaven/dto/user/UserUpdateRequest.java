package com.projectraven.ProjectRaven.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest (
    @NotBlank
    @Size(min = 3, max = 50)
    String username
){}
