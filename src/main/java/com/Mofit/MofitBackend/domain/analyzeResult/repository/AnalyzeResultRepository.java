package com.Mofit.MofitBackend.domain.analyzeResult.repository;

import com.Mofit.MofitBackend.domain.analyzeResult.entity.AnalyzeResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyzeResultRepository extends JpaRepository<AnalyzeResult, Long> {
}
