package com.example.pokemon.models;


import com.example.pokemon.controller.TrainerController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.OnMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "t_pk_trainers")
@Builder
@AllArgsConstructor
public class Trainers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String insignias;

    @NotNull
    private int level;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Capture> captures;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TrainersGym> trainersGyms;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<PokestopsTrainers> pokestopTrainers;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;


    public EntityModel<Trainers> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(TrainerController.class).show(id)).withSelfRel(),
                linkTo(methodOn(TrainerController.class).show(id)).withRel("destroy")
        );
    }


}
