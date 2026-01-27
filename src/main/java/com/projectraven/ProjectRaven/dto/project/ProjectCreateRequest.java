package com.projectraven.ProjectRaven.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record ProjectCreateRequest (
    @NotBlank
    @Size(min = 3, max = 100)
    String name,

    @Size(max = 500) String description
){}