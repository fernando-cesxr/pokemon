package com.example.pokemon.controllers;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.Gym;
import com.example.pokemon.repository.GymRepository;
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
@RequestMapping("/api/gyms")
public class GymController {

    @Autowired
    GymRepository gymRepository;

    @Autowired
    PagedResourcesAssembler assembler;


    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false)String search, @PageableDefault(size = 10) Pageable pageable){
        if (search != null){
            Page<Gym> gym = gymRepository.findByNameContaining(search, pageable);
            return assembler.toModel(gym.map(Gym::toEntityModel));
        }
        Page<Gym> gym = gymRepository.findAll(pageable);
        return assembler.toModel(gym.map(Gym::toEntityModel));
    }

    @GetMapping("{id}")
    public EntityModel<Gym> show(@PathVariable Long id){
        var gym = gymRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Gym not found"));

        return gym.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<Gym> create(@RequestBody @Valid Gym gym){
        gymRepository.save(gym);
        return ResponseEntity.status(HttpStatus.CREATED).body(gym);
    }

    @PutMapping("{id}")
    public EntityModel<Gym> update(@PathVariable Long id, @RequestBody @Valid Gym gym){
        gymRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Gym not found"));

        gym.setId(id);
        gymRepository.save(gym);
        return gym.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Gym> destroy(@PathVariable Long id){
        var gym = gymRepository.findById(id).orElseThrow(()-> new RestNotFoundException("Gym not found"));

        gymRepository.delete(gym);
        return ResponseEntity.noContent().build();
    }

}
