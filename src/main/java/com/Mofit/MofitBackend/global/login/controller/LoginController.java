package com.Mofit.MofitBackend.global.login.controller;

import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.login.dto.*;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name="회원가입/로그인")
public class LoginController {
    private final LoginService loginService;


    //loginId 중복 확인
    @PostMapping
    @Operation(summary = "loginId 중복 확인")
    public ApiResponse<LoginIdCheckResponse> checkLoginIdDuplicate(@RequestBody LoginIdCheckRequest loginIdCheckRequest){
        return new ApiResponse<>(200, LoginIdCheckResponse.from(loginService.checkLoginIdDuplicate(loginIdCheckRequest.getLoginId())));
    }

    //회원가입
    @Transactional
    @PostMapping("/join")
    @Operation(summary = "회원가입")
    public ApiResponse<EmailResponse> join(@RequestBody JoinRequest joinRequest){
        User user = loginService.join(joinRequest);
        return new ApiResponse<>(201, EmailResponse.from(user));
    }
    //로그인
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public void login(@RequestBody LoginRequest loginRequest){
    }

    //구글 로그인


    //비밀번호 변경
    @Transactional
    @PatchMapping("/passwordPatch")
    @Operation(summary = "비밀번호 변경")
    public ApiResponse<String> patchPassword(@RequestBody PasswordRequest passwordRequest){
        loginService.patchPassword(passwordRequest);
        return new ApiResponse<>(200, null);
    }


}
