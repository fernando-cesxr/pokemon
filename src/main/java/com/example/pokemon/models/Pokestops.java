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
@Entity(name = "t_pk_pokestops")
@Builder
@AllArgsConstructor
public class Pokestops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String location;

    @OneToMany(mappedBy = "pokestops", cascade = CascadeType.ALL)
    private List<ItensPokestops> itensPokestops;

    @OneToMany(mappedBy = "pokestops", cascade = CascadeType.ALL)
    private List<PokestopTrainers> pokestopTrainers;
    
}
