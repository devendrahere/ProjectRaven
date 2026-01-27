package com.projectraven.ProjectRaven.dto.project;



public record ProjectResponse (
         Long id,
         String name,
         String description,
         Long createdAt,
         Long updatedAt
){}
