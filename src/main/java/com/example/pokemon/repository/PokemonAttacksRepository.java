package com.example.pokemon.repository;

import com.example.pokemon.models.PokemonAttacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonAttacksRepository extends JpaRepository<PokemonAttacks, Long> {
}
