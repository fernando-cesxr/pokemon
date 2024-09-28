package com.example.pokemon.models;

import com.example.pokemon.controllers.PokestopTrainersController;
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
@AllArgsConstructor
@Entity(name = "t_pk_pokestopsTrainers")
@Builder
@NoArgsConstructor
public class PokestopsTrainers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPokestops")
    private Pokestops pokestops;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;

    public EntityModel<PokestopsTrainers> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(PokestopTrainersController.class).show(id)).withSelfRel(),
                linkTo(methodOn(PokestopTrainersController.class).show(id)).withRel("destroy")
        );
    }


}
