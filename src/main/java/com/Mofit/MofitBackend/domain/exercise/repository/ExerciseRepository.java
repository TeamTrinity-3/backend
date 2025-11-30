package com.Mofit.MofitBackend.domain.exercise.repository;

import com.Mofit.MofitBackend.domain.exercise.entity.Exercise;
import com.Mofit.MofitBackend.domain.exercise.entity.TargetArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findById(Long id);
    Page<Exercise> findByTargetArea(TargetArea targetArea, Pageable pageable);
    Page<Exercise> findAll(Pageable pageable);

    @Query("SELECT e FROM Exercise e " +
            "WHERE LOWER(e.exerciseName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(e.intro) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Exercise> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
