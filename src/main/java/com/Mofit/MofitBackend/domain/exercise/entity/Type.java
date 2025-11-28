package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    STRENGTH_TRAINING("근력운동"), CARDIO("유산소"),
    STRETCHING("스트레칭"), CORE("코어"),
    BALANCE("밸런스"), PLYOMETRICS("플라이오메트릭");

    private final String type;

    @JsonCreator
    public static Type from(String value){
        for(Type type : Type.values()){
            if(type.type.equals(value)){
                return type;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}
