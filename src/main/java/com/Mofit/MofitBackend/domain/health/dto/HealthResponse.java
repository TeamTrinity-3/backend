package com.Mofit.MofitBackend.domain.health.dto;

import com.Mofit.MofitBackend.domain.health.entity.Health;
import com.Mofit.MofitBackend.domain.health.entity.RestrictArea;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
public class HealthResponse {
    private String birth;
    private String gender;
    private float height;
    private float weight;
    private String proficiency;
    private String place;
    private Set<String> restrictAreas;

    public static HealthResponse from(Health health){
        return HealthResponse.builder()
                .birth(health.getBirth())
                .gender(health.getGender().getGender())
                .height(health.getHeight())
                .weight(health.getWeight())
                .proficiency(health.getProficiency().getProficiency())
                .place(health.getPlace().getPlace())
                .restrictAreas(health.getRestrictAreas()
                        .stream()
                        .map(RestrictArea::getRestrictArea)
                        .collect(Collectors.toSet()))
                .build();
    }
}
