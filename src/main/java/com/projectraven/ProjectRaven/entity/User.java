package com.projectraven.ProjectRaven.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "users",
        uniqueConstraints={
                @UniqueConstraint(columnNames = "email")
        }
)
@Getter
@Setter
@AllArgsConstructor
public class User extends BaseEntity{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 50)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false, length = 30)
        private String name;

        protected User() {}
}