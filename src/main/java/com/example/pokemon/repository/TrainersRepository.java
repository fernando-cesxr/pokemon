package com.example.pokemon.repository;

import com.example.pokemon.models.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainersRepository extends JpaRepository<Trainers, Long> {
}
