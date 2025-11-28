package com.Mofit.MofitBackend.domain.detail.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    DESCRIPTION("설명"), CAUTION("주의사항");

    private final String category;

    @JsonCreator
    public static Category from(String value){
        for(Category category : Category.values()){
            if(category.category.equals(value)){
                return category;
            }
        }
        throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
    }
}
