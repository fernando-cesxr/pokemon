package com.example.pokemon.repository;

import com.example.pokemon.models.Pokestops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokestopsRepository extends JpaRepository<Pokestops, Long> {
}
