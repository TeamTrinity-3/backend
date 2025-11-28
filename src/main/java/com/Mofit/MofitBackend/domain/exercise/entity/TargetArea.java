package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetArea {
    FULL_BODY("전신",".png"), UPPER_BODY("상체",".png"),
    LOWER_BODY("하체",".png"), ABDOMEN("복부",".png"),
    CHEST("가슴",".png"), BACK("등",".png"),
    SHOULDERS("어깨",".png"), ARMS("팔",".png"),
    CARDIO("유산소",".png");

    private final String targetArea;
    private final String image;

    @JsonCreator
    public static TargetArea from(String value){
//        for(TargetArea targetArea : TargetArea.values()){
//            if(targetArea.targetArea.equals(value)){
//                return targetArea;
//            }
//        }
//        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
        try {
            return TargetArea.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
        }

    }

}
