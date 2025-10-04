package com.Mofit.MofitBackend.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //LOGIN
    WRONG_PASSWORD(401, "비밀번호가 틀렸습니다."),


    //NOT_VALID
    NOT_VALID_ERROR(400, "잘못된 파라미터입니다.");
    private final int status;
    private final String message;
}
