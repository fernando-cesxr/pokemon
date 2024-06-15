package com.example.pokemon.models;


import com.example.pokemon.controller.AttackController;
import jakarta.validation.constraints.Null;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "t_pk_attacks")
@Builder
@AllArgsConstructor
public class Attacks {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String type;

    private int damage;

    @OneToMany(mappedBy = "attacks", cascade = CascadeType.ALL)
    private List<PokemonAttacks> pokemonAttacks;

    public EntityModel<Attacks> toEntityModel(){
    return EntityModel.of(
            this,
            linkTo(methodOn(AttackController.class).show(id)).withSelfRel(),
            linkTo(methodOn(AttackController.class).show(id)).withRel("destroy")
    );
    }

}
