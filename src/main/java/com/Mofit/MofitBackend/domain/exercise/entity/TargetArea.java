package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetArea {
    FULL_BODY("전신"), UPPER_BODY("상체"),
    LOWER_BODY("하체"), ABDOMEN("복부"),
    CHEST("가슴"), BACK("등"),
    SHOULDERS("어깨"), ARMS("팔"),
    CARDIO("유산소");

    private final String targetArea;

    @JsonCreator
    public static TargetArea from(String value){
        for(TargetArea targetArea : TargetArea.values()){
            if(targetArea.targetArea.equals(value)){
                return targetArea;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }

}
