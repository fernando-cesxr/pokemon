package com.example.pokemon.repository;

import com.example.pokemon.models.PokestopsTrainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokestopsTrainersRepository extends JpaRepository<PokestopsTrainers, Long> {
}
