package com.Mofit.MofitBackend.domain.analyzeResult.entity;

import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum Persona {

    BALANCED_ATHLETE("운동과 친구 타입", "💪",
            "모든 체력 요소가 고르게 발달한 균형잡힌 체력을 가진 타입",
            List.of("전반적으로 평균 이상의 체력", "꾸준한 운동 습관 보유", "다양한 운동을 즐김"),
            "현재 수준을 유지하면서 약한 부분을 집중 보완하세요."),
    STRENGTH_FOCUSED("파워 헬창 타입", "🏋️",
            "근력이 매우 뛰어난 파워형 체력의 소유자",
            List.of("뛰어난 근력과 파워", "웨이트 트레이닝 선호", "상대적으로 유연성/심폐지구력 부족"),
            "유산소 운동과 스트레칭을 추가하여 균형을 맞추세요."),
    CARDIO_MASTER("두 개의 심장 타입", "🏃",
        "심폐지구력이 탁월한 지구력형 타입",
            List.of("뛰어난 심폐지구력", "장거리 운동에 강함", "러닝/사이클링 등 유산소 운동 선호"),
            "근력 운동을 추가하여 부상을 예방하세요."),
    FLEXIBILITY_KING("부드러운 우연성 타입", "🧘",
        "유연성이 뛰어난 밸런스형 체력의 소유자",
        List.of("탁월한 유연성", "요가/필라테스 적합", "부드러운 움직임"),
            "근력과 심폐지구력을 강화하여 전반적인 체력을 향상시키세요."),

    BEGINNER("파릇파릇 새싹 타입", "🌱",
        "운동을 이제 막 시작하거나 기초 체력이 필요한 타입",
        List.of("전반적으로 낮은 체력 수준", "운동 경험 부족", "체계적인 운동 계획 필요"),
        "가벼운 운동부터 시작하여 점진적으로 강도를 높이세요."),

    WEAK_CORE("종이인간 타입", "📄",
        "근력과 코어가 약한 타입으로 기초 체력 강화가 필요한 타입",
        List.of("약한 근력과 코어", "자세 불안정", "쉽게 피로감을 느낌"),
       "코어 운동과 기초 근력 운동에 집중하세요.");

    private final String name;
    private final String emoji;
    private final String description;
    private final List<String> characteristics;
    private final String recommendation;

    @JsonCreator
    public static Persona from(String value){
        try {
            return Persona.valueOf(value.toUpperCase());
        } catch (Exception e){
            throw new BusinessException(ErrorCode.NOT_VALID_ENUM_VALUE);
        }
    }
}
