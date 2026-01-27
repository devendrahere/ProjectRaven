package com.projectraven.ProjectRaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "projects"
)
@Getter
@Setter
@AllArgsConstructor
public class Projects  extends  BaseEntity{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 150)
        private String name;

        @Column(columnDefinition = "TEXT")
        private String description;

        @ManyToOne(fetch = FetchType.LAZY,optional = false)
        @JoinColumn(name = "created_by", nullable = false)
        private User createdBy;

        public Projects(){}
}
