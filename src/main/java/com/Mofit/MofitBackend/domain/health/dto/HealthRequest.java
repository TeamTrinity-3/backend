package com.Mofit.MofitBackend.domain.health.dto;

import com.Mofit.MofitBackend.domain.health.entity.*;
import com.Mofit.MofitBackend.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class HealthRequest {
    private Gender gender;
    private String birth;
    private float height;
    private float weight;
    private Proficiency proficiency;
    private Place place;
    private Set<RestrictArea> restrictAreas;
}
