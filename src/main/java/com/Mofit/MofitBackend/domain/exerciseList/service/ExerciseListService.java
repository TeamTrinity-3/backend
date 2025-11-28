package com.Mofit.MofitBackend.domain.exerciseList.service;

import com.Mofit.MofitBackend.domain.exerciseList.dto.ListResponse;
import com.Mofit.MofitBackend.domain.exerciseList.dto.RoutineResponse;
import com.Mofit.MofitBackend.domain.exerciseList.entity.ExerciseList;
import com.Mofit.MofitBackend.domain.exerciseList.repository.ExerciseListRepository;
import com.Mofit.MofitBackend.domain.exercisePlan.entity.ExercisePlan;
import com.Mofit.MofitBackend.domain.exercisePlan.repository.ExercisePlanRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseListService {
    private final LoginService loginService;
    private final ExercisePlanRepository exercisePlanRepository;
    private final ExerciseListRepository exerciseListRepository;
    //특정 일차 운동 목록 불러오기
    public RoutineResponse getExerciseList(String loginId, Long planId){
        User user = loginService.getLoginUser(loginId);
        ExercisePlan exercisePlan = exercisePlanRepository.findById(planId)
                .orElseThrow(()-> new BusinessException(ErrorCode.PLAN_DOESNT_EXIST));

        if(!exercisePlan.getUser().getId().equals(user.getId())){
            throw new BusinessException(ErrorCode.NOT_VALID_ACCESS);
        }

        List<ExerciseList> exerciseLists = exerciseListRepository.findAllByExercisePlanIdOrderBySequenceAsc(planId);

        List<ListResponse> listResponses = exerciseLists.stream()
                .map(ListResponse::from)
                .toList();

        return RoutineResponse.from(exercisePlan, listResponses);
    }
}
