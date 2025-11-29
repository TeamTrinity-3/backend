package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetArea {
    FULL_BODY("전신","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/full_body.png"),
    UPPER_BODY("상체","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/upper_body.png"),
    LOWER_BODY("하체","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/lower_body.png"),
    ABDOMEN("복부","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/abdomen.png"),
    CHEST("가슴","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/chest.png"),
    BACK("등","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/back.png"),
    SHOULDERS("어깨","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/shoulder.png"),
    ARMS("팔","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/arm.png"),
    CARDIO("유산소","https://mofit-image.s3.ap-northeast-2.amazonaws.com/category/cardio.png");

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
