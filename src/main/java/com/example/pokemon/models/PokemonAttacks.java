package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "t_pk_pokemonAttacks")
@Builder
@AllArgsConstructor
public class PokemonAttacks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String attackType; // fast or charged

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idAttacks")
    private Attacks attacks;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPokemon")
    private Pokemon pokemon;
}
