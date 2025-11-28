package com.Mofit.MofitBackend.domain.exercise.dto;

import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExerciseListItemResponse {
    private Long exerciseId;
    private String image;
    private String exerciseName;
    private String intro;
    private int totalSec;

    public static ExerciseListItemResponse from(Exercise exercise){
        int totalSec = exercise.getDurationSec()*exercise.getSets() + exercise.getRestSec()*(Math.max(exercise.getSets()-1, 0));

        return ExerciseListItemResponse.builder()
                .exerciseId(exercise.getId())
                .image(exercise.getImage())
                .exerciseName(exercise.getExerciseName())
                .intro(exercise.getIntro())
                .totalSec(totalSec)
                .build();
    }
}
