package com.Mofit.MofitBackend.domain.Health.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Health extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private float height;

    private float weight;

    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;

    @Enumerated(EnumType.STRING)
    private Place place;

    @Enumerated(EnumType.STRING)
    private RestrictArea restrictArea;
}
