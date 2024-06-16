package com.example.pokemon.models;

import com.example.pokemon.controller.PokemonController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PokemonAttacks> pokemonAttacks;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Capture> captures;

    public EntityModel<Pokemon> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(PokemonController.class).show(id)).withSelfRel(),
                linkTo(methodOn(PokemonController.class).show(id)).withRel("destroy")
        );
    }

}
