package com.Mofit.MofitBackend.domain.health.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Place {
    HOME("집"), GYM("헬스장");

    private final String place;

    @JsonCreator
    public static Place from(String value){
        for(Place place : Place.values()){
            if(place.place.equals(value)){
                return place;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}
