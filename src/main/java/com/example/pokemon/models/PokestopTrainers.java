package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_pokestopsTrainers")
@Builder
@NoArgsConstructor
public class PokestopTrainers {

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

}
