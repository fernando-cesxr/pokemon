package com.example.pokemon.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    public long id;

    @NotBlank
    public String name;

    @NotBlank
    public String insignias;

    @NotBlank
    public int level;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<Capture> captures;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<TrainersGym> trainersGyms;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List <ItensTrainers> itensTrainers;

    @OneToMany(mappedBy = "trainers", cascade = CascadeType.ALL)
    private List<PokestopTrainers> pokestopTrainers;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;





}
