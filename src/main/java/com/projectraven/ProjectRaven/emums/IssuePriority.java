package com.projectraven.ProjectRaven.emums;

public enum IssuePriority {
    LOW,
    // for low issues that can be resolved in future updates
    MEDIUM,
    // for issues that should be addressed but are not urgent
    HIGH,
    // for important issues that need prompt attention
    CRITICAL,
    // for severe issues that require immediate action
}