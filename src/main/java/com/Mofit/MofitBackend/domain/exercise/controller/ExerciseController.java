package com.Mofit.MofitBackend.domain.exercise.controller;

import com.Mofit.MofitBackend.domain.exercise.dto.ExerciseResponse;
import com.Mofit.MofitBackend.domain.exercise.service.ExerciseService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
@Tag(name="운동")
public class ExerciseController {
    private final ExerciseService exerciseService;
    //특정 운동 세부 내용 불러오기
    @GetMapping("/get/{exerciseId}")
    @Operation(summary = "운동 세부 내용 불러오기")
    public ApiResponse<ExerciseResponse> getExercise(Authentication authentication, @PathVariable("exerciseId") Long exerciseId){
        ExerciseResponse exerciseResponse = exerciseService.getExercise(authentication.getName(), exerciseId);
        return new ApiResponse<>(200, exerciseResponse);
    }
}
