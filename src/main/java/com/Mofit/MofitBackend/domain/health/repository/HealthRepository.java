package com.Mofit.MofitBackend.domain.health.repository;

import com.Mofit.MofitBackend.domain.health.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthRepository extends JpaRepository<Health, Long> {
    Optional<Health> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
