package com.example.weatherprojectcdi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "expiresat")
    private LocalDateTime expiresAt;

    public Session(User user, LocalDateTime expiresAt) {
        this.user = user;
        this.expiresAt = expiresAt;
    }
}