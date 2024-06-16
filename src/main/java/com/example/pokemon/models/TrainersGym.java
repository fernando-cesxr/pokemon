package com.example.pokemon.models;

import com.example.pokemon.controller.TrainersGymController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.IdentityHashMap;

@Data
@NoArgsConstructor
@Entity(name = "t_pk_trainersGym")
@Builder
@AllArgsConstructor
public class TrainersGym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idGym")
    private Gym gym;


    public EntityModel<TrainersGym> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(TrainersGymController.class).show(id)).withSelfRel(),
                linkTo(methodOn(TrainersGymController.class).show(id)).withRel("destroy")
        );
    }
}
