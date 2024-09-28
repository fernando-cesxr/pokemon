package com.example.pokemon.models;


import com.example.pokemon.controllers.CaptureController;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_capture")
@Builder
@NoArgsConstructor
public class Capture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Nullable
    private LocalDate date_capture;

    @NotNull
    private String capture_location;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPokemon")
    private Pokemon pokemon;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;


    public EntityModel<Capture> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(CaptureController.class).show(id)).withSelfRel(),
                linkTo(methodOn(CaptureController.class).show(id)).withRel("destroy")
        );
    }

}
