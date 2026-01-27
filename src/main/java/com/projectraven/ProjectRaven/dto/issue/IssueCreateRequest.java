package com.projectraven.ProjectRaven.dto.issue;

import com.projectraven.ProjectRaven.emums.IssuePriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class IssueCreateRequest {
    @NotBlank
    @Size(min = 5, max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull
    private IssuePriority priority;
}
