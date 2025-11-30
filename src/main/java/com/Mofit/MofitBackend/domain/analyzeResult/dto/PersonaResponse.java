package com.Mofit.MofitBackend.domain.analyzeResult.dto;

import com.Mofit.MofitBackend.domain.analyzeResult.entity.Persona;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PersonaResponse {
    private String name;
    private String emoji;
    private String description;
    private List<String> characteristics;
    private String recommendation;

    public static PersonaResponse from(Persona persona){
        return PersonaResponse.builder()
                .name(persona.getName())
                .emoji(persona.getEmoji())
                .description(persona.getDescription())
                .characteristics(persona.getCharacteristics())
                .recommendation(persona.getRecommendation())
                .build();
    }
}
