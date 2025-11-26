package com.Mofit.MofitBackend.domain.exercisePlan.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PlanListResponse {
    private int currentDay;
    private List<PlanResponse> plans;

    public static PlanListResponse from(int currentDay, List<PlanResponse> planResponses){
        return PlanListResponse.builder()
                .currentDay(currentDay)
                .plans(planResponses)
                .build();
    }
}
