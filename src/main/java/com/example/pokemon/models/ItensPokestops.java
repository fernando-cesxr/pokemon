package com.example.pokemon.models;

import jakarta.persistence.*;
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

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idItens")
    private Itens itens;

    @EmbeddedId
    @ManyToOne
    @JoinColumn(name = "idPokestops")
    private Pokestops pokestops;

}
