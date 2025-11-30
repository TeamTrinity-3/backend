package com.Mofit.MofitBackend.domain.analyzeResult.repository;

import com.Mofit.MofitBackend.domain.analyzeResult.entity.AnalyzeResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnalyzeResultRepository extends JpaRepository<AnalyzeResult, Long> {
    Optional<AnalyzeResult> findByUserId(Long userId);
}
