package com.Mofit.MofitBackend.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //LOGIN
    WRONG_PASSWORD(401, "아이디와 비밀번호가 일치하지 않습니다."),
    GOOGLE_ACCOUNT_EXIST(409, "구글 회원가입 계정입니다. 구글 로그인을 이용해주세요."),

    LOCAL_ACCOUNT_EXIST(409, "일반 회원가입 계정입니다. 일반 로그인을 이용해주세요."),

    OTHER_ACCOUNT_EXIST(409, "다른 방식으로 회원가입한 계정입니다."),

    UNABLE_CHANGE_PASSWORD(403, "SNS 회원가입 계정은 비밀번호를 변경할 수 없습니다."),

    //USER
    USER_DOESNT_EXIST(404, "존재하지 않는 회원입니다."),

    //HEALTH
    HEALTH_DOESNT_EXIST(404,"건강정보가 존재하지 않습니다."),

    HEALTH_ALREADY_EXIST(409, "건강정보가 이미 존재합니다."),

    //STAMINA
    STAMINA_DOESNT_EXIST(404, "체력정보가 존재하지 않습니다."),

    STAMINA_ALREADY_EXIST(409, "체력정보가 이미 존재합니다."),

    //TOKEN
    TOKEN_NOT_PROVIDED(401, "토큰이 없거나 형식이 잘못되었습니다."),

    TOKEN_EXPIRED(401, "만료된 토큰입니다."),

    //NOT_VALID
    NOT_VALID_ERROR(400, "잘못된 파라미터입니다."),

    NOT_VALID_ENUM_VALUE(400, "잘못된 enum 값입니다.");
    private final int status;
    private final String message;
}
