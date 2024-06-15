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
@Entity(name = "t_pk_pokestops")
@Builder
@AllArgsConstructor
public class Pokestops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String location;

    @OneToMany(mappedBy = "pokestops", cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<ItensPokestops> itensPokestops;

    @OneToMany(mappedBy = "pokestops", cascade = CascadeType.ALL)
//    @JsonBackReference
    private List<PokestopsTrainers> pokestopTrainers;

}
