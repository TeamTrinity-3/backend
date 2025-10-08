package com.Mofit.MofitBackend.global.login.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginIdCheckResponse {
    private  boolean available;

    public static LoginIdCheckResponse from(boolean check){
        boolean available;
        if(check){
            available = false;
        }
        else{
            available = true;
        }
        return LoginIdCheckResponse.builder()
                .available(available)
                .build();
    }
}
