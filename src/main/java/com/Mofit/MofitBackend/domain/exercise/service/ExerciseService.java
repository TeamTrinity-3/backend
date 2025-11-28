package com.Mofit.MofitBackend.domain.exercise.service;

import com.Mofit.MofitBackend.domain.detail.dto.DetailResponse;
import com.Mofit.MofitBackend.domain.detail.entity.Detail;
import com.Mofit.MofitBackend.domain.detail.repository.DetailRepository;
import com.Mofit.MofitBackend.domain.exercise.dto.ExerciseResponse;
import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import com.Mofit.MofitBackend.domain.exercise.repository.ExerciseRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final LoginService loginService;
    private final ExerciseRepository exerciseRepository;
    private final DetailRepository detailRepository;
    //특정 운동 세부 사항 불러오기
    public ExerciseResponse getExercise(String loginId, Long exerciseId){
        User user = loginService.getLoginUser(loginId);
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(()-> new BusinessException(ErrorCode.EXERCISE_DOESNT_EXIST));

        List<Detail> details = detailRepository.findAllByExerciseIdOrderByCategoryAscSequenceAsc(exerciseId);

        List<DetailResponse> detailResponses = details.stream()
                .map(DetailResponse::from)
                .toList();

        return ExerciseResponse.from(exercise, detailResponses);
    }
}
