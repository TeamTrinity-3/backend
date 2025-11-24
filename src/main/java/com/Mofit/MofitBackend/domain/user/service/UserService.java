package com.Mofit.MofitBackend.domain.user.service;

import com.Mofit.MofitBackend.domain.user.dto.NameRequest;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.domain.user.repository.UserRepository;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final LoginService loginService;
    private final UserRepository userRepository;
    //회원 이름 저장하기
    @Transactional
    public User setUserName(String loginId, NameRequest nameRequest){
        User user = loginService.getLoginUser(loginId);
        user.setName(nameRequest.getName());
        userRepository.save(user);
        return user;
    }
}
