package com.example.pokemon.repository;

import com.example.pokemon.models.Itens;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensRepository extends JpaRepository<Itens, Long> {
    Page<Itens> findByNameContaining(String search, Pageable pageable);
}
