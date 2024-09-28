package com.example.pokemon.controllers;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Pokestops;
import com.example.pokemon.repository.PokestopsRepository;
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
@RequestMapping("/api/pokestops")
public class PokestopsController {

    @Autowired
    PokestopsRepository pokestopsRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 10)Pageable pageable){
        Page<Pokestops> pokestops = pokestopsRepository.findAll(pageable);

        return assembler.toModel(pokestops.map(Pokestops::toEntityModel));
    }

    @GetMapping("/findByName")
    public PagedModel<EntityModel<Object>> searchName(@RequestParam(required = true) String search,
                                                      @PageableDefault(size= 10) Pageable pageable ){
        Page<Pokestops> pokestops = pokestopsRepository.findByNameContaining(search, pageable);
        return assembler.toModel(pokestops.map(Pokestops::toEntityModel));
    }


    @GetMapping("{id}")
    public EntityModel<Pokestops> show(@PathVariable Long id){
        var pokestops = pokestopsRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Pokestop not found"));

        return pokestops.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<Pokestops> create(@RequestBody @Valid Pokestops pokestops){
        pokestopsRepository.save(pokestops);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokestops);
    }

    @PutMapping("{id}")
    public EntityModel<Pokestops> update(@PathVariable Long id, @RequestBody @Valid Pokestops pokestops){
        pokestopsRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Pokestop not found"));

        pokestops.setId(id);
        pokestopsRepository.save(pokestops);

        return pokestops.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pokestops> destroy(@PathVariable Long id){
        var pokestops = pokestopsRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Pokestop not found"));

        pokestopsRepository.delete(pokestops);
        return ResponseEntity.noContent().build();
    }





}
