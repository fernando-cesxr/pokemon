package com.example.pokemon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "t_pk_tokemonAttacks")
@Builder
@AllArgsConstructor
public class PokemonAttacks {

    private String attackType; // fast or charged

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idAttacks")
    private Attacks attacks;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idPokemon")
    private Pokemon pokemon;
}
