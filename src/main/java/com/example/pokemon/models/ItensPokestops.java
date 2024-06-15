package com.example.pokemon.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity(name = "t_pk_itensPokestops")
@Builder
@NoArgsConstructor
public class ItensPokestops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idItens")
    private Itens itens;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPokestops")
    private Pokestops pokestops;

}
