package com.Mofit.MofitBackend.domain.user.controller;

import com.Mofit.MofitBackend.domain.user.dto.NameRequest;
import com.Mofit.MofitBackend.domain.user.dto.NameResponse;
import com.Mofit.MofitBackend.domain.user.dto.ProfileResponse;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.domain.user.service.UserService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name="회원")
public class UserController {
    private final UserService userService;

    //이름 변경하기
//    @Transactional
//    @PostMapping("/patch/name")
//    @Operation(summary = "회원 정보 입력")
//    public ApiResponse<NameResponse> setUserName(Authentication authentication, @RequestBody NameRequest nameRequest){
//        User user = userService.setUserName(authentication.getName(), nameRequest);
//        return new ApiResponse<>(200, NameResponse.from(user));
//    }

    //회원 프로필 정보 불러오기
    @GetMapping("/get")
    @Operation(summary = "이름, 프로필 사진 불러오기")
    public ApiResponse<ProfileResponse> getUserProfile(Authentication authentication){
        ProfileResponse profileResponse = userService.getUserProfile(authentication.getName());
        return new ApiResponse<>(200, profileResponse);
    }

    //회원 탈퇴하기
    @Transactional
    @DeleteMapping("/delete")
    @Operation(summary = "회원 탈퇴하기")
    public ApiResponse<String> deleteUser(Authentication authentication){
        userService.deleteUser(authentication.getName());
        return new ApiResponse<>(204, null);
    }
}
