package com.example.pokemon.models;


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

    @NotNull
    private String insignias;

    @NotNull
    private int level;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<Capture> captures;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<TrainersGym> trainersGyms;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<PokestopTrainers> pokestopTrainers;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;





}
