package com.example.pokemon.controller;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Attacks;
import com.example.pokemon.repository.AttacksRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PagedResourcesAssembler;

@RestController
@RequestMapping("/api/attacks")
public class AttackController {

    @Autowired
    AttacksRepository attacksRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>>index(@PageableDefault(size = 10) Pageable pageable){
        Page<Attacks> attacks = attacksRepository.findAll(pageable);
        return assembler.toModel(attacks.map(Attacks::toEntityModel));
    }

    @GetMapping("/findByName")
    public PagedModel<EntityModel<Object>> searchName(@RequestParam(required = true) String search,
                                                      @PageableDefault(size = 10) Pageable pageable){
        Page<Attacks> attacks = attacksRepository.findByNameContaining(search, pageable);
        return assembler.toModel(attacks.map(Attacks::toEntityModel));
    }


    @GetMapping("{id}")
    public EntityModel<Attacks> show(@PathVariable Long id){
        var attacks = attacksRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Attack not found"));
        return attacks.toEntityModel();
    }



    @PostMapping
    public ResponseEntity<Attacks> create(@RequestBody @Valid Attacks attacks){
        attacksRepository.save(attacks);
        return ResponseEntity.status(HttpStatus.CREATED).body(attacks);
    }

    @PutMapping("{id}")
    public EntityModel<Attacks> update(@PathVariable Long id, @RequestBody @Valid Attacks attacks){
        attacksRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Attack not found"));

        attacks.setId(id);
        attacksRepository.save(attacks);

        return attacks.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Attacks> destroy(@PathVariable Long id){
        var attacks =  attacksRepository.findById(id).orElseThrow(() -> new RestNotFoundException("Attacks not found"));

        attacksRepository.delete(attacks);
        return ResponseEntity.noContent().build();
    }


}


