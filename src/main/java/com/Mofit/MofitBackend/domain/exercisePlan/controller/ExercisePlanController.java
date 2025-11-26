package com.Mofit.MofitBackend.domain.exercisePlan.controller;

import com.Mofit.MofitBackend.domain.exercisePlan.dto.PlanListResponse;
import com.Mofit.MofitBackend.domain.exercisePlan.service.ExercisePlanService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
@Tag(name="운동 일정")
public class ExercisePlanController {
    private final ExercisePlanService exercisePlanService;
    //일주일 일정 진행 상황 보내주기
    @GetMapping("/get")
    @Operation(summary = "일정 진행 상황 불러오기")
    public ApiResponse<PlanListResponse> getExercisePlan(Authentication authentication){
        PlanListResponse planListResponse = exercisePlanService.getExercisePlan(authentication.getName());
        return new ApiResponse<>(200, planListResponse);
    }
}
