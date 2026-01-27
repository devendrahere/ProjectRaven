package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.emums.IssuePriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IssueCreateRequest (
    @NotBlank
    @Size(min = 5, max = 100)
    String title,

    @Size(max = 1000) String description,

    @NotNull IssuePriority priority
){}
