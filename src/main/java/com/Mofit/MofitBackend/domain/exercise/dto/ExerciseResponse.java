package com.Mofit.MofitBackend.domain.exercise.dto;

import com.Mofit.MofitBackend.domain.detail.dto.DetailResponse;
import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import com.Mofit.MofitBackend.domain.health.entity.RestrictArea;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class ExerciseResponse {
    private String exerciseName;
    private String type;
    private String difficulty;
    private String image;
    private String intro;
    private String equipment;
    private String targetArea;
    private String primaryMuscle;
    private int durationSec;
    private int reps;
    private int sets;
    private int restSec;
    private Set<String> restrictAreas;
    private List<DetailResponse> detailResponses;

    public static ExerciseResponse from(Exercise exercise, List<DetailResponse> detailResponses){
        return ExerciseResponse.builder()
                .exerciseName(exercise.getExerciseName())
                .type(exercise.getType().getType())
                .difficulty(exercise.getDifficulty().getDifficulty())
                .image(exercise.getImage())
                .intro(exercise.getIntro())
                .equipment(exercise.getEquipment().getEquipment())
                .targetArea(exercise.getTargetArea().getTargetArea())
                .primaryMuscle(exercise.getPrimaryMuscle().getPrimaryMuscle())
                .durationSec(exercise.getDurationSec())
                .reps(exercise.getReps())
                .sets(exercise.getSets())
                .restSec(exercise.getRestSec())
                .restrictAreas(exercise.getRestrictAreas()
                        .stream()
                        .map(RestrictArea::getRestrictArea)
                        .collect(Collectors.toSet()))
                .detailResponses(detailResponses)
                .build();
    }

}
