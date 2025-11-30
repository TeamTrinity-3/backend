package com.Mofit.MofitBackend.domain.analyzeResult.service;

import com.Mofit.MofitBackend.domain.analyzeResult.dto.AnalyzeResultResponse;
import com.Mofit.MofitBackend.domain.analyzeResult.entity.AnalyzeResult;
import com.Mofit.MofitBackend.domain.analyzeResult.repository.AnalyzeResultRepository;
import com.Mofit.MofitBackend.domain.user.entity.User;
import com.Mofit.MofitBackend.global.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyzeResultService {
    private final LoginService loginService;
    private final AnalyzeResultRepository analyzeResultRepository;
    public AnalyzeResultResponse getAnalyzeResult(String loginId){
        User user = loginService.getLoginUser(loginId);
        AnalyzeResult analyzeResult = analyzeResultRepository.findByUserId(user.getId()).orElse(null);
        if (analyzeResult == null){
            return null;
        }
        return AnalyzeResultResponse.from(analyzeResult);
    }
}
