package com.Mofit.MofitBackend.domain.exercisePlan.dto;

import com.Mofit.MofitBackend.domain.exercisePlan.entity.ExercisePlan;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PlanResponse {
    private Long planId;
    private int day;
    private boolean active;
    private boolean progress;

    public static PlanResponse from(ExercisePlan exercisePlan, int currentDay){
        return PlanResponse.builder()
                .planId(exercisePlan.getId())
                .day(exercisePlan.getDay())
                .active(exercisePlan.getDay()<=currentDay)
                .progress(exercisePlan.isProgress())
                .build();

    }
}
