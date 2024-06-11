package com.example.pokemon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_pkoestopsTrainers")
@Builder
@NoArgsConstructor
public class PokestopTrainers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idPokestops")
    private Pokestops pokestops;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idTrainers")
    private Trainers trainers;

}
