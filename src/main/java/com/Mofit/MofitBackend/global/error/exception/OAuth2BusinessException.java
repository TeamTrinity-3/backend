package com.Mofit.MofitBackend.global.error.exception;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import lombok.Getter;

import javax.naming.AuthenticationException;
@Getter
public class OAuth2BusinessException extends AuthenticationException {
    private final ErrorCode errorCode;
    public OAuth2BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
