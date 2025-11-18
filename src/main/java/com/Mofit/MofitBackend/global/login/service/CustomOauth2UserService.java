package com.Mofit.MofitBackend.global.login.service;

import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.domain.user.entity.UserRole;
import com.Mofit.MofitBackend.domain.user.repository.UserRepository;
import com.Mofit.MofitBackend.global.error.ErrorCode;
import com.Mofit.MofitBackend.global.error.exception.BusinessException;
import com.Mofit.MofitBackend.global.login.details.CustomOauth2UserDetails;
import com.Mofit.MofitBackend.global.login.details.GoogleUserDetails;
import com.Mofit.MofitBackend.global.login.details.OAuth2UserInfo;
import com.Mofit.MofitBackend.global.login.dto.TokenResponse;
import com.Mofit.MofitBackend.global.login.filter.LoginFilter;
import com.Mofit.MofitBackend.global.login.util.JwtUtil;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}",oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo oAuth2UserInfo = null;

        // 뒤에 진행할 다른 소셜 서비스 로그인을 위해 구분 => 구글
        if(provider.equals("google")){
            log.info("구글 로그인");
            oAuth2UserInfo = new GoogleUserDetails(oAuth2User.getAttributes());

        }

        String providerId = oAuth2UserInfo.getProviderId();
        String loginId = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getName();
        String picture = oAuth2UserInfo.getPicture();

        //이미 동일 이메일로 다른 방식의 회원가입을 한 경우
        Optional<User> existUser = userRepository.findByLoginId(loginId);
        User user;
        if(existUser.isPresent()){
            User userCheck = existUser.get();
            if((userCheck.getProvider() != null) && userCheck.getProvider().equals("google") && (provider.equals("google"))){
                user = userCheck;
            }
            else{
                throw new OAuth2AuthenticationException(new OAuth2Error("409", "Account exists with a different login method", null));
            }
        } //첫 회원가입일 경우
        else{
            user = User.builder()
                    .loginId(loginId)
                    .name(name)
                    .picture(picture)
                    .provider(provider)
                    .providerId(providerId)
                    .userRole(UserRole.USER)
                    .build();
            userRepository.save(user);
        }

        return new CustomOauth2UserDetails(user, oAuth2User.getAttributes());
    }
}
