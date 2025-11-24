package com.Mofit.MofitBackend.domain.stamina.service;

import com.Mofit.MofitBackend.domain.stamina.dto.StaminaRequest;
import com.Mofit.MofitBackend.domain.stamina.entity.Stamina;
import com.Mofit.MofitBackend.domain.stamina.repository.StaminaRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StaminaService {
    private final LoginService loginService;
    private final StaminaRepository staminaRepository;

    //체력 측정 결과 저장
    @Transactional
    public void setStamina(String loginId, StaminaRequest staminaRequest){
        User user = loginService.getLoginUser(loginId);
        if(staminaRepository.existsByUserId(user.getId())){
            throw new BusinessException(ErrorCode.STAMINA_ALREADY_EXIST);
        }
        Stamina stamina = new Stamina(user);
        stamina.patchStamina(staminaRequest);
        staminaRepository.save(stamina);
    }

    //체력 측정 결과 수정
    @Transactional
    public void patchStamina(String loginId, StaminaRequest staminaRequest){
        User user = loginService.getLoginUser(loginId);
        Stamina stamina = staminaRepository.findByUserId(user.getId())
                .orElseThrow(()-> new BusinessException(ErrorCode.STAMINA_DOESNT_EXIST));
        stamina.patchStamina(staminaRequest);
        staminaRepository.save(stamina);
    }
}
