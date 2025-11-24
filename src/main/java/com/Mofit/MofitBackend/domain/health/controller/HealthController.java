package com.Mofit.MofitBackend.domain.health.controller;

import com.Mofit.MofitBackend.domain.health.dto.HealthRequest;
import com.Mofit.MofitBackend.domain.health.dto.HealthResponse;
import com.Mofit.MofitBackend.domain.health.entity.Health;
import com.Mofit.MofitBackend.domain.health.service.HealthService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@Tag(name="건강정보")
public class HealthController {
    private final HealthService healthService;
    //건강 정보 입력
    @Transactional
    @PostMapping("/info")
    @Operation(summary = "건강 정보 입력")
    public ApiResponse<String> setUserHealth(Authentication authentication, @RequestBody HealthRequest healthRequest){
        healthService.setUserHealth(authentication.getName(), healthRequest);
        return new ApiResponse<>(201, null);
    }

    //건강 정보 불러오기
    @GetMapping("/get")
    @Operation(summary = "건강 정보 불러오기")
    public ApiResponse<HealthResponse> getUserHealth(Authentication authentication){
        HealthResponse healthResponse = healthService.getUserHealth(authentication.getName());
        return new ApiResponse<>(200, healthResponse);
    }

    //건강 정보 수정
    @Transactional
    @PatchMapping("/patch")
    @Operation(summary = "건강 정보 수정하기")
    public ApiResponse<HealthResponse> patchUserHealth(Authentication authentication, @RequestBody HealthRequest healthRequest){
        HealthResponse healthResponse = healthService.patchUserHealth(authentication.getName(), healthRequest);
        return new ApiResponse<>(200, healthResponse);
    }
}
