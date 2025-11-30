package com.Mofit.MofitBackend.domain.analyzeResult.controller;

import com.Mofit.MofitBackend.domain.analyzeResult.dto.AnalyzeResultResponse;
import com.Mofit.MofitBackend.domain.analyzeResult.service.AnalyzeResultService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
@Tag(name="분석 결과")
public class AnalyzeResultController {
    private final AnalyzeResultService analyzeResultService;
    @GetMapping("/get")
    @Operation(summary = "분석 결과 불러오기")
    public ApiResponse<AnalyzeResultResponse> getAnalyzeResult(Authentication authentication){
        AnalyzeResultResponse analyzeResultResponse = analyzeResultService.getAnalyzeResult(authentication.getName());
        return new ApiResponse<>(200, analyzeResultResponse);
    }
}
