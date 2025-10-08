package com.Mofit.MofitBackend.global.login.service;

import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.domain.user.repository.UserRepository;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.dto.JoinRequest;
import com.Mofit.MofitBackend.global.login.dto.PasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //id 중복 확인
    public boolean checkLoginIdDuplicate(String loginId){
        return userRepository.existsByLoginId(loginId);
    }
    //회원가입
    @Transactional
    public User join(JoinRequest joinRequest){
        joinRequest.setPassword(bCryptPasswordEncoder.encode(joinRequest.getPassword()));
        User user = joinRequest.toEntity();

        userRepository.save(user);
        return user;
    }

    //로그인

    //로그인한 회원 정보
    public User getLoginUser(String loginId){
        return userRepository.findByLoginId(loginId).orElseThrow(()->new BusinessException(ErrorCode.USER_DOESNT_EXIST));
    }
    //비밀번호 변경
    @Transactional
    public void patchPassword(PasswordRequest passwordRequest){
        User user = getLoginUser(passwordRequest.getLoginId());
        user.setPassword(bCryptPasswordEncoder.encode(passwordRequest.getPassword()));
        userRepository.save(user);
    }
}
