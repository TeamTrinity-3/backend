package com.Mofit.MofitBackend.domain.user.dto;

import com.Mofit.MofitBackend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProfileResponse {
    private String name;
    private String picture;

    public static ProfileResponse from(User user){
        return ProfileResponse.builder()
                .name(user.getName())
                .picture(user.getPicture())
                .build();
    }
}
