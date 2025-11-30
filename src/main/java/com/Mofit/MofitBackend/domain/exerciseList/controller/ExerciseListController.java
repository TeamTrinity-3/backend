package com.Mofit.MofitBackend.domain.exerciseList.controller;

import com.Mofit.MofitBackend.domain.exerciseList.dto.RoutineResponse;
import com.Mofit.MofitBackend.domain.exerciseList.service.ExerciseListService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
@Tag(name="운동 목록")
public class ExerciseListController {
    private final ExerciseListService exerciseListService;

    //특정 일차 운동 목록 불러오기
    @GetMapping("/get/{planId}")
    @Operation(summary = "운동 목록 불러오기")
    public ApiResponse<RoutineResponse> getExerciseList(@PathVariable("planId") Long planId, Authentication authentication){
        RoutineResponse routineResponse = exerciseListService.getExerciseList(authentication.getName(), planId);
        return new ApiResponse<>(200, routineResponse);
    }
}
