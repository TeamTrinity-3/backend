package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.detail.entity.Detail;
import com.Mofit.MofitBackend.domain.health.entity.RestrictArea;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercise extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(length = 100)
    @Size(max = 100)
    private String exerciseName;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    private String intro;
    @Enumerated(EnumType.STRING)
    private Equipment equipment;
    private String image;
    @Enumerated(EnumType.STRING)
    private TargetArea targetArea;
    @Enumerated(EnumType.STRING)
    private PrimaryMuscle primaryMuscle;
    private int durationSec;
    private int reps;
    private int sets;
    private int restSec;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "exercise_restricts", joinColumns = @JoinColumn(name = "exercise_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "exercise_restrict")
    private Set<RestrictArea> restrictAreas = new HashSet<>();
    private float mets;
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detail> details;


}
