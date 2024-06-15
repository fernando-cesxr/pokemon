package com.example.pokemon.repository;

import com.example.pokemon.models.PokestopTrainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokestopTrainersRepository extends JpaRepository<PokestopTrainers, Long> {
}
