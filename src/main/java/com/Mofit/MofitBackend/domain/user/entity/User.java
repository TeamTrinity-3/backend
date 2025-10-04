package com.Mofit.MofitBackend.domain.user.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String loginId;

    @NotNull
    private String name;

    private String password;

    private String provider;

    private String providerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
