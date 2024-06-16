package com.example.pokemon.controller;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String search, @PageableDefault(size = 10)Pageable pageable){
        Page<Pokemon> pokemon = pokemonRepository.findAll(pageable);
        return assembler.toModel(pokemon.map(Pokemon::toEntityModel));
    }


    @GetMapping("{id}")
    public EntityModel<Pokemon> show(@PathVariable Long id){
        var pokemon = pokemonRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Pokemon not found"));
        return pokemon.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody @Valid Pokemon pokemon){
        pokemonRepository.save(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
    }

    @PutMapping("{id}")
    public EntityModel<Pokemon> update(@PathVariable Long id, @RequestBody Pokemon pokemon){
        pokemonRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Pokemon not found"));

        pokemon.setId(id);
        pokemonRepository.save(pokemon);

        return pokemon.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pokemon> destroy(@PathVariable Long id){
        var pokemon =pokemonRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Pokemon not found"));

        pokemonRepository.delete(pokemon);

        return ResponseEntity.noContent().build();
    }

}
