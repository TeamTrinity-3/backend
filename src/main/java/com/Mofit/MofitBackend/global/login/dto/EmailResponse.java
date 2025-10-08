package com.Mofit.MofitBackend.global.login.dto;

import com.Mofit.MofitBackend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailResponse {
    private Long userId;

    private String loginId;

    public static EmailResponse from(User user){
        return EmailResponse.builder()
                .userId(user.getId())
                .loginId(user.getLoginId())
                .build();
    }
}
