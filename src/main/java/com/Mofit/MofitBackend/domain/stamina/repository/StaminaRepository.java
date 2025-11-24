package com.Mofit.MofitBackend.domain.stamina.repository;

import com.Mofit.MofitBackend.domain.stamina.entity.Stamina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaminaRepository extends JpaRepository<Stamina, Long> {
    Optional<Stamina> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
