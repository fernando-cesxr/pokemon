package com.example.pokemon.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@Entity(name = "t_pk_pokemon")
@Builder
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String type;

    private double height;

    @NotNull
    private String firstAttack;

    @NotNull
    private String secondAttack;

    private int nr_Attack;

    private int nr_Defense;

    private int nr_Hp;

    private int level;

    @OneToMany(mappedBy = "pokemon")
    @JsonIgnore
    private List<PokemonAttacks> pokemonAttacks;

    @OneToMany(mappedBy = "pokemon")
    @JsonIgnore
    private List<Capture> captures;

}
