package com.example.weatherprojectcdi.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
@Data
@Table(name = "users")
public class User {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
