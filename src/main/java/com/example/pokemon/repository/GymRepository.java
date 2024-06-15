package com.example.pokemon.repository;

import com.example.pokemon.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long> {
}
