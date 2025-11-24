package com.Mofit.MofitBackend.domain.health.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Proficiency {
    BEGINNER("입문자"), INTERMEDIATE("초중급자"), ADVANCED("숙련자");

    private final String proficiency;

    @JsonCreator
    public static Proficiency from(String value){
        for(Proficiency proficiency : Proficiency.values()){
            if(proficiency.proficiency.equals(value)){
                return proficiency;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}
