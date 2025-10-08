package com.Mofit.MofitBackend.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //LOGIN
    WRONG_PASSWORD(401, "비밀번호가 틀렸습니다."),

    //USER
    USER_DOESNT_EXIST(404, "존재하지 않는 회원입니다."),

    //TOKEN
    TOKEN_NOT_PROVIDED(401, "토큰이 없거나 형식이 잘못되었습니다."),

    TOKEN_EXPIRED(401, "만료된 토큰입니다."),

    //NOT_VALID
    NOT_VALID_ERROR(400, "잘못된 파라미터입니다.");
    private final int status;
    private final String message;
}
