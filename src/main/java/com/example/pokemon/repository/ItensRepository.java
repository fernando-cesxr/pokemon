package com.example.pokemon.repository;

import com.example.pokemon.models.Itens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensRepository extends JpaRepository<Itens, Long> {
}
