package com.Mofit.MofitBackend.global.login.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        String error = URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8);

        //host 파악
        String host = request.getHeader("Host");

        String redirectUrl;
//        if(host!=null && host.contains("localhost")){
//            redirectUrl = "http://"+host+"/login/failure?error=" + error;
//        }
//        else{
//            redirectUrl = "https://mefoweb.com/login/failure?error=" + error;
//        }
        redirectUrl = "http://localhost:5173/login/failure?error="+error;
        response.sendRedirect(redirectUrl);

    }
}
