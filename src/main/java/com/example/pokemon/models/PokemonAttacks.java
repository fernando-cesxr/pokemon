package com.example.pokemon.models;

import com.example.pokemon.controller.PokemonAttacksController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    @ManyToOne
    @JoinColumn(name = "idAttacks")
    private Attacks attacks;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPokemon")
    private Pokemon pokemon;


    public EntityModel<PokemonAttacks> toEntityModel(){
    return EntityModel.of(
            this,
            linkTo(methodOn(PokemonAttacksController.class).show(id)).withSelfRel(),
            linkTo(methodOn(PokemonAttacksController.class).show(id)).withRel("destroy")
    );
    }
}
