package com.projectraven.ProjectRaven.mapper;

import com.projectraven.ProjectRaven.dto.user.UserSummary;
import com.projectraven.ProjectRaven.entity.ProjectMember;
import com.projectraven.ProjectRaven.entity.User;

public class ProjectMemberMapper {
    private ProjectMemberMapper(){
    }

    public static UserSummary toUserSummary(ProjectMember projectMember){
        User user = projectMember.getUser();
        return UserMapper.toSummary(user);
    }
}
