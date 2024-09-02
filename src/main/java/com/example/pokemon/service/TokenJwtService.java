package com.example.pokemon.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.pokemon.models.Credencial;
import com.example.pokemon.models.JwtToken;
import com.example.pokemon.models.User;
import com.example.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenJwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository repository;

    @Autowired
    private Environment env;

    public JwtToken generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .withSubject(credencial.email())
                .withIssuer("Pokemon")
                .sign(alg);
        return new JwtToken(token);
    }

    public User validate(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                .withIssuer("Pokemon")
                .build()
                .verify(token)
                .getSubject();
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("invalid token"));
    }
}