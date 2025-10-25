package com.Mofit.MofitBackend.global.login.util;

import com.Mofit.MofitBackend.global.login.details.CustomOauth2UserDetails;
import com.Mofit.MofitBackend.global.login.dto.TokenResponse;
import com.Mofit.MofitBackend.global.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{

        CustomOauth2UserDetails customOauth2UserDetails = (CustomOauth2UserDetails) authentication.getPrincipal();
        String username = customOauth2UserDetails.getUsername();

        // role 추출
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // JWTUtil에 token 생성 요청
        String token = jwtUtil.createJwt(username, role, 60*60*1000L);

        response.setContentType("application/json;charset=UTF-8");

        // ApiResponse 객체 만들기
        ApiResponse<TokenResponse> apiResponse = new ApiResponse<>(200, TokenResponse.from(username, "Bearer " + token));

        // JSON으로 직렬화해서 응답 바디에 쓰기
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);
        response.getWriter().write(jsonResponse);
    }
}
