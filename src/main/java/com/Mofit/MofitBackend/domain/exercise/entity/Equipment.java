package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Equipment {
    BODY_WEIGHT("맨몸"), MAT("매트"),
    DUMBBELL("덤벨"), BARBELL("바벨"),
    BAND("밴드"), FOAM_ROLLER("폼롤러"),
    KETTLE_BELL("케틀벨"),GYM_BALL("짐볼"),
    PULL_UP_BAR("철봉"), MACHINE("머신"),
    BENCH("벤치");

    private final String equipment;

    @JsonCreator
    public static Equipment from(String value){
        for(Equipment equipment : Equipment.values()){
            if(equipment.equipment.equals(value)){
                return equipment;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}
