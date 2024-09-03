package com.example.pokemon.repository;

import com.example.pokemon.models.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Page<Pokemon> findByNameContaining(String search, Pageable pageable);
}
