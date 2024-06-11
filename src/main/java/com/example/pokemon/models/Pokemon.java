package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    public Long id;

    @NotBlank
    public String name;

    @NotBlank
    public String type;

    @NotBlank
    public double height;

    @NotBlank
    public String firstAttack;

    @NotBlank
    public String secondAttack;

    @NotBlank
    public int nr_Attack;

    @NotBlank
    public int nr_Defense;

    @NotBlank
    public int nr_Hp;

    @NotBlank
    public int level;

    @OneToMany(mappedBy = "pokemon")
    private List<PokemonAttacks> pokemonAttacks;

    @OneToMany(mappedBy = "pokemon")
    private List<Capture> captures;


}
