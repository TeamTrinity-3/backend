package com.Mofit.MofitBackend.domain.exercise.service;

import com.Mofit.MofitBackend.domain.detail.dto.DetailResponse;
import com.Mofit.MofitBackend.domain.detail.entity.Detail;
import com.Mofit.MofitBackend.domain.detail.repository.DetailRepository;
import com.Mofit.MofitBackend.domain.exercise.dto.ExerciseListItemResponse;
import com.Mofit.MofitBackend.domain.exercise.dto.ExerciseResponse;
import com.Mofit.MofitBackend.domain.exercise.dto.TargetAreaResponse;
import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import com.Mofit.MofitBackend.domain.exercise.entity.TargetArea;
import com.Mofit.MofitBackend.domain.exercise.repository.ExerciseRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    //카테고리 목록 보내주기
    public List<TargetAreaResponse> getTargetArea(){
        return Arrays.stream(TargetArea.values())
                .map(TargetAreaResponse::from)
                .collect(Collectors.toList());
    }

    //운동 카테고리 검색하기
    public Page<ExerciseListItemResponse> getExerciseListItem(TargetArea targetArea, Pageable pageable){
        if(targetArea == null){
            return exerciseRepository.findAll(pageable)
                    .map(ExerciseListItemResponse::from);
        }

        return exerciseRepository.findByTargetArea(targetArea, pageable)
                .map(ExerciseListItemResponse::from);
    }
}
