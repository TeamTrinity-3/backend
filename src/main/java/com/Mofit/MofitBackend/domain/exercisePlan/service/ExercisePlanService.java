package com.Mofit.MofitBackend.domain.exercisePlan.service;

import com.Mofit.MofitBackend.domain.exercisePlan.dto.PlanListResponse;
import com.Mofit.MofitBackend.domain.exercisePlan.dto.PlanResponse;
import com.Mofit.MofitBackend.domain.exercisePlan.entity.ExercisePlan;
import com.Mofit.MofitBackend.domain.exercisePlan.repository.ExercisePlanRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercisePlanService {
    private final LoginService loginService;
    private final ExercisePlanRepository exercisePlanRepository;
    //일정 진행 상황 보내주기
    public PlanListResponse getExercisePlan(String loginId){
        User user = loginService.getLoginUser(loginId);
        List<ExercisePlan> exercisePlans = exercisePlanRepository
                .findAllByUserIdOrderByDayAsc(user.getId());

        //활성화 여부 계산
        int currentDay = getCurrentDay(exercisePlans);

        List<PlanResponse> planResponses = exercisePlans.stream()
                .map(plan -> PlanResponse.from(plan, currentDay))
                .toList();

        return PlanListResponse.from((int)currentDay, planResponses);
    }

    //운동 진행 완료 상태로 바꾸기
    @Transactional
    public PlanResponse patchExercisePlanProgress(String loginId, Long planId){
        User user = loginService.getLoginUser(loginId);

        //요청한 회원의 plan인지 검증 및, currentDay 계산용
        List<ExercisePlan> exercisePlans = exercisePlanRepository
                .findAllByUserIdOrderByDayAsc(user.getId());

        ExercisePlan exercisePlan = exercisePlans.stream()
                        .filter(p -> p.getId().equals(planId))
                        .findFirst()
                        .orElseThrow(() -> new BusinessException(ErrorCode.PLAN_DOESNT_EXIST));

        //활성화 여부 계산
        int currentDay = getCurrentDay(exercisePlans);

        //오늘의 plan이 아닌 경우
        if(currentDay!=exercisePlan.getDay()){
            throw new BusinessException(ErrorCode.NOT_VALID_ACCESS);
        }

        //이미 완료된 상태일 경우
        if(exercisePlan.isProgress()){
            throw new BusinessException(ErrorCode.PLAN_ALREADY_COMPLETED);
        }

        exercisePlan.setProgress(true);
        exercisePlanRepository.save(exercisePlan);

        return PlanResponse.from(exercisePlan, currentDay+1);
    }

    //current day 계산 함수
    private int getCurrentDay(List<ExercisePlan> exercisePlans){
        for(ExercisePlan plan : exercisePlans){
            if(!plan.isProgress()){
                return plan.getDay();
            }
        }
        return -1;
    }
}
