package com.Mofit.MofitBackend.domain.exercisePlan.service;

import com.Mofit.MofitBackend.domain.exercisePlan.dto.PlanListResponse;
import com.Mofit.MofitBackend.domain.exercisePlan.dto.PlanResponse;
import com.Mofit.MofitBackend.domain.exercisePlan.entity.ExercisePlan;
import com.Mofit.MofitBackend.domain.exercisePlan.repository.ExercisePlanRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercisePlanService {
    private final LoginService loginService;
    private final ExercisePlanRepository exercisePlanRepository;
    //일정 진행 상황 보내주기
    public PlanListResponse getExercisePlan(String loginId){
        User user = loginService.getLoginUser(loginId);
        List<ExercisePlan> exercisePlans = exercisePlanRepository.findAllByUserIdOrderByDayAsc(user.getId());

        //활성화 여부 계산
        LocalDate startDate = exercisePlans.get(0).getCreatedAt().toLocalDate();
        LocalDate today = LocalDate.now();
        long currentDay = ChronoUnit.DAYS.between(startDate, today)+1;

        List<PlanResponse> planResponses = exercisePlans.stream()
                .map(plan -> PlanResponse.from(plan, (int)currentDay))
                .toList();

        return PlanListResponse.from((int)currentDay, planResponses);
    }
}
