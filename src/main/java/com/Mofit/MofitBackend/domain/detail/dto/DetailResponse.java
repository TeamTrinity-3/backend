package com.Mofit.MofitBackend.domain.detail.dto;

import com.Mofit.MofitBackend.domain.detail.entity.Detail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailResponse {
    private String category;
    private int sequence;
    private String content;

    public static DetailResponse from(Detail detail){
        return DetailResponse.builder()
                .category(detail.getCategory().getCategory())
                .sequence(detail.getSequence())
                .content(detail.getContent())
                .build();
    }
}
