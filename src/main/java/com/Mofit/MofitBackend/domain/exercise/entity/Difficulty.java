package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {
    HARD("상"), MEDIUM("중"), EASY("하");

    private final String difficulty;

    @JsonCreator
    public static Difficulty from(String value){
        for(Difficulty difficulty: Difficulty.values()){
            if(difficulty.difficulty.equals(value)){
                return difficulty;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }


}
