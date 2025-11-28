package com.Mofit.MofitBackend.domain.exercise.repository;

import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import com.Mofit.MofitBackend.domain.exercise.entity.TargetArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findById(Long id);
    Page<Exercise> findByTargetArea(TargetArea targetArea, Pageable pageable);
    Page<Exercise> findAll(Pageable pageable);
}
