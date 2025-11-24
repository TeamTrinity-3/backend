package com.Mofit.MofitBackend.domain.health.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestrictArea {
    NECK("목"),
    WAIST("허리"),
    ELBOWS("팔꿈치"),
    WRISTS("손목"),
    HAMSTRINGS("햄스트링"),
    KNEES("무릎"),
    SHOULDERS("어깨"),
    ANKLES("발목");

    private final String restrictArea;

    @JsonCreator
    public static RestrictArea from(String value){
        for(RestrictArea restrictArea : RestrictArea.values()){
            if(restrictArea.restrictArea.equals(value)){
                return restrictArea;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}