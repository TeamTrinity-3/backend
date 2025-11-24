package com.Mofit.MofitBackend.domain.detail.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Detail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotNull
    private int sequence;
    @NotNull
    private String content;

}
