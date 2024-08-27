package com.example.pokemon.repository;

import com.example.pokemon.models.Pokestops;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokestopsRepository extends JpaRepository<Pokestops, Long> {
    Page<Pokestops> findByNameContaining(String search, Pageable pageable);
}
