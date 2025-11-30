package com.Mofit.MofitBackend.domain.exercisePlan.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.exerciseList.entity.ExerciseList;
import com.Mofit.MofitBackend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExercisePlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int day;
    private boolean progress;
    private String image;
    private String title;
    private String description;

    @OneToMany(mappedBy = "exercisePlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseList> exerciseLists;
}
