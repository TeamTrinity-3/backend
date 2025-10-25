package com.Mofit.MofitBackend.test.controller;

import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.login.dto.EmailResponse;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Tag(name="테스트용")
public class TestController {

    private final LoginService loginService;
    @GetMapping("/loginUser")
    @Operation(summary = "로그인한 회원 확인")
    public ApiResponse<EmailResponse> getLoginUser(Authentication authentication){
        User user = loginService.getLoginUser(authentication.getName());
        return new ApiResponse<>(200, EmailResponse.from(user));
    }
}
