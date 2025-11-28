package com.Mofit.MofitBackend.domain.detail.repository;

import com.Mofit.MofitBackend.domain.detail.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findAllByExerciseIdOrderByCategoryAscSequenceAsc(Long id);
}
