package com.projectraven.ProjectRaven.dto.user;

import lombok.Getter;

@Getter
public class UserSummary {
    private final Long id;
    private final String username;

    public UserSummary(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
