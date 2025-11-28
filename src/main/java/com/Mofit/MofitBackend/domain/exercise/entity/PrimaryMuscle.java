package com.Mofit.MofitBackend.domain.exercise.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrimaryMuscle {
    TRAPEZIUS("승모근"), DELTOID("삼각근"),
    PECTORALIS_MAJOR("대흉근"), LATISSIMUS_DORSI("광배근"),
    ERECTOR_SPINAE("척추기립근"), BICEPS_BRACHII("상완이두근"),
    TRICEPS_BRACHII("상완삼두근"), GLUTEUS_MAXIMUS("대둔근"),
    QUADRICEPS_FEMORIS("대퇴사두근"), HAMSTRINGS("햄스트링"),
    ADDUCTORS("내전근"), GASTROCNEMIUS("비복근"),
    RECTUS_ABDOMINIS("복직근"), EXTERNAL_OBLIQUE("외복사근"),
    GENERAL_MUSCLES("전신근육");

    private final String primaryMuscle;

    @JsonCreator
    public static PrimaryMuscle from(String value){
        for(PrimaryMuscle primaryMuscle : PrimaryMuscle.values()){
            if(primaryMuscle.primaryMuscle.equals(value)){
                return primaryMuscle;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}
