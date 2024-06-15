package com.example.pokemon.repository;

import com.example.pokemon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
