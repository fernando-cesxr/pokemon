package com.example.pokemon.repository;

import com.example.pokemon.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
    Page<User> findByEmailContaining(String search, Pageable pageable);
}
