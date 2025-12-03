package com.Mofit.MofitBackend.global.login.util;

import com.Mofit.MofitBackend.global.login.details.CustomOauth2UserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{

        CustomOauth2UserDetails customOauth2UserDetails = (CustomOauth2UserDetails) authentication.getPrincipal();
        String username = customOauth2UserDetails.getUsername();

        //임시 코드 발급
        String tempCode = jwtUtil.createTempJwt(username, 60 * 1000L);

        //redirect
        String redirectUrl = "https://mefoweb.com/login/success?code="+tempCode;
        response.sendRedirect(redirectUrl);
    }
}
