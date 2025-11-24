package com.Mofit.MofitBackend.domain.health.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    FEMALE("여자"), MALE("남자");

    private final String gender;

    @JsonCreator
    public static Gender from(String value){
        for(Gender gender : Gender.values()){
            if(gender.gender.equals(value)){
                return gender;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}
