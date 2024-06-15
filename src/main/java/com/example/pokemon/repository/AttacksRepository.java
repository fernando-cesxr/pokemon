package com.example.pokemon.repository;

import com.example.pokemon.models.Attacks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttacksRepository extends JpaRepository<Attacks, Long>{
    Page<Attacks> findByNameContaining(String busca, Pageable pageable);
}
