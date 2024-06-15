package com.example.pokemon.repository;

import com.example.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}
