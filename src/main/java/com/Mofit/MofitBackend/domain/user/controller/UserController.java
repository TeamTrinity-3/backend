package com.Mofit.MofitBackend.domain.user.controller;

import com.Mofit.MofitBackend.domain.user.dto.NameRequest;
import com.Mofit.MofitBackend.domain.user.dto.NameResponse;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.domain.user.service.UserService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name="회원")
public class UserController {
    private final UserService userService;

    //초기 회원 정보 입력
    @Transactional
    @PostMapping("/info")
    @Operation(summary = "회원 정보 입력")
    public ApiResponse<NameResponse> setUserName(Authentication authentication, @RequestBody NameRequest nameRequest){
        User user = userService.setUserName(authentication.getName(), nameRequest);
        return new ApiResponse<>(200, NameResponse.from(user));
    }
}
