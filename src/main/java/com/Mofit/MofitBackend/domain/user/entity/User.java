package com.Mofit.MofitBackend.domain.user.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.exercisePlan.entity.ExercisePlan;
import com.Mofit.MofitBackend.domain.health.entity.Health;
import com.Mofit.MofitBackend.domain.stamina.entity.Stamina;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String loginId;

    private String name;

    private String picture;

    private String password;

    private String provider;

    private String providerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Health health;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Stamina stamina;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private AnalyzeResult analyzeResult;
//
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExercisePlan> exercisePlans;
}
