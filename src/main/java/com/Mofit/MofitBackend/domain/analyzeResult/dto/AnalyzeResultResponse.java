package com.Mofit.MofitBackend.domain.analyzeResult.dto;

import com.Mofit.MofitBackend.domain.analyzeResult.entity.AnalyzeResult;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnalyzeResultResponse {
    private int perStrength;
    private int perCardio;
    private int perCore;
    private int perFlexibility;
    private int perAgility;
    private int perBodyComposition;
    private float averageScore;
    private PersonaResponse personaResponse;
    private String llmReport;

    public static AnalyzeResultResponse from(AnalyzeResult analyzeResult){
        return AnalyzeResultResponse.builder()
                .perStrength(analyzeResult.getPerStrength())
                .perCardio(analyzeResult.getPerCardio())
                .perCore(analyzeResult.getPerCore())
                .perFlexibility(analyzeResult.getPerFlexibility())
                .perAgility(analyzeResult.getPerAgility())
                .perBodyComposition(analyzeResult.getPerBodyComposition())
                .averageScore(analyzeResult.getAverageScore())
                .personaResponse(PersonaResponse.from(analyzeResult.getPersona()))
                .llmReport(analyzeResult.getLlmReport())
                .build();
    }
}
