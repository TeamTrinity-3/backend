package com.Mofit.MofitBackend.global.login.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class LoginIdCheckResponse {
    private  boolean available;
    private String provider;

    private String message;

    public static LoginIdCheckResponse from(String duplicate){
        boolean available;
        String provider;
        String message;
        if(Objects.equals(duplicate, "null")){
            available = true;
            provider = null;
            message = "사용 가능한 이메일입니다.";
        }
        else{
            available = false;
            if(duplicate==null){
                provider = "local";
                message = "이미 사용 중인 이메일입니다.";
            }
            else {
                provider = "SNS";
                message = "SNS 회원가입한 이메일입니다.";
            }
        }
        return LoginIdCheckResponse.builder()
                .available(available)
                .provider(provider)
                .message(message)
                .build();
    }
}
