package com.example.pokemon.repository;

import com.example.pokemon.models.ItensPokestops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPokestopsRepository extends JpaRepository<ItensPokestops, Long> {
}
