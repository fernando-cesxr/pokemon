package com.example.pokemon.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;

    private String insignias;

    @NotNull
    private int level;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<Capture> captures;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<TrainersGym> trainersGyms;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<PokestopsTrainers> pokestopTrainers;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;





}
