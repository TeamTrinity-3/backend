package com.Mofit.MofitBackend.domain.user.dto;

import com.Mofit.MofitBackend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NameResponse {
    private String loginId;
    private String name;

    public static NameResponse from(User user){
        return NameResponse.builder()
                .loginId(user.getLoginId())
                .name(user.getName())
                .build();
    }
}
