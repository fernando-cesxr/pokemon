package com.example.pokemon.repository;

import com.example.pokemon.models.Gym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Page<Gym> findByNameContaining(String search, Pageable pageable);
}
