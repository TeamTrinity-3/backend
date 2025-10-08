package com.Mofit.MofitBackend.global.login.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenResponse {
    private String loginId;
    private String token;
    public static TokenResponse from(String loginId, String token){
        return TokenResponse.builder()
                .loginId(loginId)
                .token(token)
                .build();
    }
}
