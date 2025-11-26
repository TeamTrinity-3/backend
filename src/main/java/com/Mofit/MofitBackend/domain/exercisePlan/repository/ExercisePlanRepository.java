package com.Mofit.MofitBackend.domain.exercisePlan.repository;

import com.Mofit.MofitBackend.domain.exercisePlan.entity.ExercisePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExercisePlanRepository extends JpaRepository<ExercisePlan, Long> {
    List<ExercisePlan> findAllByUserIdOrderByDayAsc(Long id);
    Optional<ExercisePlan> findById(Long planId);
}
