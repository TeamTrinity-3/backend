package com.Mofit.MofitBackend.domain.exerciseList.dto;

import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import com.Mofit.MofitBackend.domain.exerciseList.entity.ExerciseList;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListResponse {
    private Long exerciseId;
    private int sequence;
    private String exerciseName;
    private int totalSec;

    public static ListResponse from(ExerciseList exerciseList){
        Exercise exercise = exerciseList.getExercise();

        int totalSec = exercise.getDurationSec()*exercise.getSets() + exercise.getRestSec()*(Math.max(exercise.getSets()-1, 0));
        return ListResponse.builder()
                .exerciseId(exerciseList.getExercise().getId())
                .sequence(exerciseList.getSequence())
                .exerciseName(exercise.getExerciseName())
                .totalSec(totalSec)
                .build();
    }
}
