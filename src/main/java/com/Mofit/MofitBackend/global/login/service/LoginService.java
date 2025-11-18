package com.Mofit.MofitBackend.global.login.service;

import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.domain.user.repository.UserRepository;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.dto.JoinRequest;
import com.Mofit.MofitBackend.global.login.dto.PasswordRequest;
import com.Mofit.MofitBackend.global.login.dto.TokenRequest;
import com.Mofit.MofitBackend.global.login.dto.TokenResponse;
import com.Mofit.MofitBackend.global.login.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    //id 중복 확인
    public String checkLoginIdDuplicate(String loginId){
        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return user.getProvider();
        }
        else{
            return "null";
        }
    }
    //회원가입
    @Transactional
    public User join(JoinRequest joinRequest){
        joinRequest.setPassword(bCryptPasswordEncoder.encode(joinRequest.getPassword()));
        User user = joinRequest.toEntity();

        userRepository.save(user);
        return user;
    }

    //소셜 로그인 jwt 토큰 발급
    public TokenResponse createJwtToken(TokenRequest tokenRequest){
        User user = getLoginUser(jwtUtil.getLoginId(tokenRequest.getTempCode()));
        // JWTUtil에 token 생성 요청
        String token = jwtUtil.createJwt(user.getLoginId(), user.getUserRole().name(), 60*60*1000L);
        return TokenResponse.from(user.getLoginId(), "Bearer " + token);
    }

    //로그인한 회원 정보
    public User getLoginUser(String loginId){
        return userRepository.findByLoginId(loginId).orElseThrow(()->new BusinessException(ErrorCode.USER_DOESNT_EXIST));
    }
    //비밀번호 변경
    @Transactional
    public void patchPassword(PasswordRequest passwordRequest){
        User user = getLoginUser(passwordRequest.getLoginId());
        if(user.getProvider()==null) {
            user.setPassword(bCryptPasswordEncoder.encode(passwordRequest.getPassword()));
            userRepository.save(user);
        }
        else throw new BusinessException(ErrorCode.UNABLE_CHANGE_PASSWORD);
    }
}
