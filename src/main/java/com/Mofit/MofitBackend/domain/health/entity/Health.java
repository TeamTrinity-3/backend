package com.Mofit.MofitBackend.domain.health.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(length = 10)
    @Size(max = 10)
    private String birth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private float height;
    private float weight;
    @Enumerated(EnumType.STRING)
    private Proficiency proficiency;
    @Enumerated(EnumType.STRING)
    private Place place;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_restricts", joinColumns = @JoinColumn(name = "health_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "user_restrict")
    private Set<RestrictArea> restrictAreas = new HashSet<>();
}
