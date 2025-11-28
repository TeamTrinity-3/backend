package com.Mofit.MofitBackend.domain.exercise.controller;

import com.Mofit.MofitBackend.domain.exercise.dto.ExerciseListItemResponse;
import com.Mofit.MofitBackend.domain.exercise.dto.ExerciseResponse;
import com.Mofit.MofitBackend.domain.exercise.dto.TargetAreaResponse;
import com.Mofit.MofitBackend.domain.exercise.entity.TargetArea;
import com.Mofit.MofitBackend.domain.exercise.service.ExerciseService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Tag(name="운동/카테고리")
public class ExerciseController {
    private final ExerciseService exerciseService;
    //특정 운동 세부 내용 불러오기
    @GetMapping("/exercise/get/{exerciseId}")
    @Operation(summary = "운동 세부 내용 불러오기")
    public ApiResponse<ExerciseResponse> getExercise(Authentication authentication, @PathVariable("exerciseId") Long exerciseId){
        ExerciseResponse exerciseResponse = exerciseService.getExercise(authentication.getName(), exerciseId);
        return new ApiResponse<>(200, exerciseResponse);
    }

    //카테고리 목록 보내주기
    @GetMapping("/targetArea")
    @Operation(summary = "카테고리 목록 불러오기")
    public ApiResponse<List<TargetAreaResponse>> getTargetArea(){
        List<TargetAreaResponse> targetAreaResponses = exerciseService.getTargetArea();
        return new ApiResponse<>(200, targetAreaResponses);
    }

    //카테고리 검색 결과 보내주기
    @GetMapping("/exercise")
    @Operation(summary = "카테고리 검색 결과 불러오기")
    public ApiResponse<Page<ExerciseListItemResponse>> getExerciseListItem(
            @RequestParam(name = "targetArea", required = false) TargetArea targetArea,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "12") int size){
        size = Math.min(size, 30);
        Pageable pageable = PageRequest.of(page, size);
        Page<ExerciseListItemResponse> listItemResponsePage = exerciseService.getExerciseListItem(targetArea, pageable);
        return new ApiResponse<>(200, listItemResponsePage);
    }
}
