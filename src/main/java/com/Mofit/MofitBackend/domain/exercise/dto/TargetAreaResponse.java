package com.Mofit.MofitBackend.domain.exercise.dto;

import com.Mofit.MofitBackend.domain.exercise.entity.TargetArea;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TargetAreaResponse {
    private String engName;
    private String korName;
    private String image;

    public static TargetAreaResponse from(TargetArea targetArea){
        return TargetAreaResponse.builder()
                .engName(targetArea.name())
                .korName(targetArea.getTargetArea())
                .image(targetArea.getImage())
                .build();
    }
}
