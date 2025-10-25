package com.Mofit.MofitBackend.global.mail.controller;

import com.Mofit.MofitBackend.global.mail.dto.AuthCodeRequest;
import com.Mofit.MofitBackend.global.mail.dto.AuthCodeResponse;
import com.Mofit.MofitBackend.global.mail.service.MailService;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "메일 전송")
public class MailController {
    private final MailService mailService;

    //인증 코드 전송하기
    @PostMapping("/login/mail")
    @Operation(summary = "이메일 전송")
    public ApiResponse<AuthCodeResponse> authCodeRequest(@RequestBody AuthCodeRequest authCodeRequest) throws MessagingException {
        String authCode = mailService.sendSimpleMessage(authCodeRequest.getEmail());
        return new ApiResponse<>(200, AuthCodeResponse.from(authCodeRequest.getEmail(), authCode));
    }
}