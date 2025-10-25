package com.Mofit.MofitBackend.global.login.dto;

import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.domain.user.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    private String loginId;
    private String password;
    private String name;

    //나머지 회원가입 정보는 추후 생성 예정

    public User toEntity(){
        return User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .userRole(UserRole.USER)
                .build();
    }
}
