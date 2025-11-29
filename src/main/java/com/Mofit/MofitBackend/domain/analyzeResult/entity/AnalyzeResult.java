package com.Mofit.MofitBackend.domain.analyzeResult.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnalyzeResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //백분위 결과
    private int perStrength;
    private int perCardio;
    private int perCore;
    private int perFlexibility;
    private int perAgility;
    private int perBodyComposition;

    //종합
    private float averageScore;

    //페르소나
    @Enumerated(EnumType.STRING)
    private Persona persona;

    //llm 결과
    private String llmReport;
}
