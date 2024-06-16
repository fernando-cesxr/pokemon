package com.example.pokemon.models;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.example.pokemon.controller.GymController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import java.util.List;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_gym")
@Builder
@NoArgsConstructor
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private String insignia;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TrainersGym> trainersGyms;


    public EntityModel<Gym> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(GymController.class).show(id)).withSelfRel(),
                linkTo(methodOn(GymController.class).show(id)).withRel("destroy")
        );
    }

}
