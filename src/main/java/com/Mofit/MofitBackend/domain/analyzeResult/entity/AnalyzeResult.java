package com.Mofit.MofitBackend.domain.analyzeResult.entity;

import com.Mofit.MofitBackend.domain.common.BaseEntity;
import com.Mofit.MofitBackend.domain.user.entity.User;
import jakarta.persistence.*;
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

    //Health 스냅샷
    private float snapHeight;
    private float snapWeight;
    private int snapAge;
    private float snapBMI;

    //Stamina 스냅샷
    private int snapPlank;
    private int snapChairSquat;
    private int snapPushUp;
    private int snapStepTest;
    private int snapForwardFold;
    private float snapBalance;

    //백분위 결과
    private int perStrength;
    private int perCardio;
    private int perCore;
    private int perFlexbility;
    private int peragility;
    private int perBodyComposition;

    //등급
    private String gradeStrength;
    private String gradeCardio;
    private String gradeCore;
    private String gradeFlexibility;
    private String gradeAgility;
    private String gradeBodyComposition;

    //종합
    private float averageScore;

    //페르소나
    private String type;
    private String name;
    private String emoji;
    private String description;
    private String strongestComponent;
    private int strongestPercentile;
    private String weakestComponent;
    private int weakestPercentile;


    private String llmReport;
}
