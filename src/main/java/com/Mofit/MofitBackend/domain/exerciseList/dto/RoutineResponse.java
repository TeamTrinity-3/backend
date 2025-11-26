package com.Mofit.MofitBackend.domain.exerciseList.dto;

import com.Mofit.MofitBackend.domain.exercisePlan.entity.ExercisePlan;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoutineResponse {
    private String image;
    private String title;
    private String description;
    private int routineSec;
    private List<ListResponse> listResponses;

    public static RoutineResponse from(ExercisePlan exercisePlan, List<ListResponse> listResponses){

        int routineSec = listResponses.stream()
                .mapToInt(ListResponse::getTotalSec)
                .sum();

        return RoutineResponse.builder()
                .image(exercisePlan.getImage())
                .title(exercisePlan.getTitle())
                .description(exercisePlan.getDescription())
                .routineSec(routineSec)
                .listResponses(listResponses)
                .build();
    }
}
