package com.Mofit.MofitBackend.domain.exerciseList.repository;

import com.Mofit.MofitBackend.domain.exerciseList.entity.ExerciseList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseListRepository extends JpaRepository<ExerciseList, Long> {
    List<ExerciseList> findAllByExercisePlanIdOrderBySequenceAsc(Long planId);
}
