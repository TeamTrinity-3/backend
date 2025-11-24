package com.Mofit.MofitBackend.domain.health.service;

import com.Mofit.MofitBackend.domain.health.dto.HealthRequest;
import com.Mofit.MofitBackend.domain.health.dto.HealthResponse;
import com.Mofit.MofitBackend.domain.health.entity.Health;
import com.Mofit.MofitBackend.domain.health.repository.HealthRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final LoginService loginService;
    private final HealthRepository healthRepository;

    //건강 정보 입력하기
    @Transactional
    public void setUserHealth(String loginId, HealthRequest healthRequest){
        User user = loginService.getLoginUser(loginId);
        if(healthRepository.existsByUserId(user.getId())){
            throw new BusinessException(ErrorCode.HEALTH_ALREADY_EXIST);
        }
        Health health = new Health(user);
        health.patchHealth(healthRequest);
        healthRepository.save(health);
    }

    //건강 정보 불러오기
    public HealthResponse getUserHealth(String loginId){
        User user = loginService.getLoginUser(loginId);
        Health health = healthRepository.findByUserId(user.getId()).orElse(null);
        if (health == null){
            return null;
        }
        return HealthResponse.from(health);
    }

    //건강 정보 수정하기
    @Transactional
    public HealthResponse patchUserHealth(String loginId, HealthRequest healthRequest){
        User user = loginService.getLoginUser(loginId);
        Health health = healthRepository.findByUserId(user.getId())
                .orElseThrow(()-> new BusinessException(ErrorCode.HEALTH_DOESNT_EXIST));
        health.patchHealth(healthRequest);
        healthRepository.save(health);
        return HealthResponse.from(health);
    }
}
