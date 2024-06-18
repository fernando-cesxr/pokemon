package com.example.pokemon.models;

import com.example.pokemon.controller.PokestopsController;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    @JsonIgnore
    private List<ItensPokestops> itensPokestops;

    @OneToMany(mappedBy = "pokestops", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PokestopsTrainers> pokestopTrainers;

    public EntityModel<Pokestops> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(PokestopsController.class).show(id)).withSelfRel(),
                linkTo(methodOn(PokestopsController.class).show(id)).withRel("destroy")

        );
    }


}
