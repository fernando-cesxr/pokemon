package com.example.pokemon.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record Credencial(String email, String password) {

    public Authentication toAuthetication(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
