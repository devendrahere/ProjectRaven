package com.projectraven.ProjectRaven.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest (

    @NotBlank
    @Size(min = 3, max = 50)
    String name,

    @NotBlank
    @Size(min = 8, max = 100)
    String password,

    @Email
    @NotBlank
    String email
){}