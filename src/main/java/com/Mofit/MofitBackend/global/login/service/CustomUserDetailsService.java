package com.Mofit.MofitBackend.global.login.service;

import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.login.details.CustomSecurityUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String loginId){
        User user = loginService.getLoginUser(loginId);
        return new CustomSecurityUserDetails(user);
    }
}
