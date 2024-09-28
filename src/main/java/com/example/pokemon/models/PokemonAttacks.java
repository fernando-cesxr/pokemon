package com.example.pokemon.models;

import com.example.pokemon.controllers.PokemonAttacksController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
