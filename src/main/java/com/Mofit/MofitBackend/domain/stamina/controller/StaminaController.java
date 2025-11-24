package com.Mofit.MofitBackend.domain.stamina.controller;

import com.Mofit.MofitBackend.domain.stamina.dto.StaminaRequest;
import com.Mofit.MofitBackend.domain.stamina.service.StaminaService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stamina")
@RequiredArgsConstructor
@Tag(name="체력")
public class StaminaController {
    private final StaminaService staminaService;
    //체력 측정 결과 저장하기
    @Transactional
    @PostMapping("/save")
    @Operation(summary = "체력 측정 결과 저장")
    public ApiResponse<String> saveStamina(Authentication authentication, @RequestBody StaminaRequest staminaRequest){
        staminaService.setStamina(authentication.getName(), staminaRequest);
        return new ApiResponse<>(201, null);
    }

    //체력 측정 결과 재저장하기
    @Transactional
    @PatchMapping("/patch")
    @Operation(summary = "체력 측정 결과 수정")
    public ApiResponse<String> patchStamina(Authentication authentication, @RequestBody StaminaRequest staminaRequest){
        staminaService.patchStamina(authentication.getName(), staminaRequest);
        return new ApiResponse<>(200, null);
    }
}
