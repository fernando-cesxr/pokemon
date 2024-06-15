package com.example.pokemon.repository;

import com.example.pokemon.models.TrainersGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainersGymRepository extends JpaRepository<TrainersGym, Long> {
}
