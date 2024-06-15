package com.example.pokemon.repository;

import com.example.pokemon.models.Capture;
import com.example.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptureRepository extends JpaRepository<Capture, Long> {
}
