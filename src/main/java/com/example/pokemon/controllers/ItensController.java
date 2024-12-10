package com.example.pokemon.controllers;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Itens;
import com.example.pokemon.repository.ItensRepository;
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
@RequestMapping("/api/itens")
public class ItensController {

    @Autowired
    ItensRepository itensRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @GetMapping
    public PagedModel<EntityModel<Object>>index(@RequestParam(required = false) String search, @PageableDefault(size = 10) Pageable pageable){
        if (search != null){
            Page<Itens> itens = itensRepository.findByNameContaining(search, pageable);
            return assembler.toModel(itens.map(Itens::toEntityModel));
        }
        Page<Itens> itens = itensRepository.findAll(pageable);

        return assembler.toModel(itens.map(Itens::toEntityModel));
    }
    @GetMapping("{id}")
    public EntityModel<Itens> show(@PathVariable Long id){
        var itens = itensRepository.findById(id).orElseThrow(()-> new RestNotFoundException("item not found"));

        return itens.toEntityModel();
    }



    @PostMapping
    public ResponseEntity<Itens> create(@RequestBody @Valid Itens itens){
        itensRepository.save(itens);
        return ResponseEntity.status(HttpStatus.CREATED).body(itens);
    }

    @PutMapping("{id}")
    public EntityModel<Itens> update(@PathVariable Long id, @RequestBody @Valid Itens itens){
        itensRepository.findById(id).orElseThrow(()-> new RestNotFoundException("item not found"));

        itens.setId(id);
        itensRepository.save(itens);
        return itens.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Itens> destroy(@PathVariable Long id){
        var itens = itensRepository.findById(id).orElseThrow(()-> new RestNotFoundException("item not found"));

        itensRepository.delete(itens);

        return ResponseEntity.noContent().build();
    }










}
