package com.example.pokemon.controllers;

import com.example.pokemon.exceptions.RestNotFoundException;
import com.example.pokemon.models.ItensPokestops;
import com.example.pokemon.repository.ItensPokestopsRepository;
import com.example.pokemon.repository.ItensRepository;
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
@RequestMapping("/api/itensPokestops")
public class ItensPokestopsController {

    @Autowired
    ItensPokestopsRepository itensPokestopsRepository;

    @Autowired
    PokestopsRepository pokestopsRepository;

    @Autowired
    ItensRepository itensRepository;

    @Autowired
    PagedResourcesAssembler assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@PageableDefault(size = 10) Pageable pageable){
        Page<ItensPokestops> itensPokestops = itensPokestopsRepository.findAll(pageable);

        return assembler.toModel(itensPokestops.map(ItensPokestops::toEntityModel));
    }

    @GetMapping("{id}")
    public EntityModel<ItensPokestops> show(@PathVariable Long id){
        var itensPokestops = itensPokestopsRepository.findById(id).orElseThrow(()-> new RestNotFoundException("itensPokestops not found"));

        return itensPokestops.toEntityModel();
    }

    @PostMapping
    public ResponseEntity<ItensPokestops> create(@RequestBody @Valid ItensPokestops itensPokestops){
        var itens = itensRepository.findById(itensPokestops.getItens().getId())
                .orElseThrow(()-> new RestNotFoundException("item not found"));

        var pokestops = pokestopsRepository.findById(itensPokestops.getPokestops().getId())
                .orElseThrow(()-> new RestNotFoundException("pokestop not found"));

        itensPokestops.setPokestops(pokestops);
        itensPokestops.setItens(itens);
        itensPokestopsRepository.save(itensPokestops);

        return ResponseEntity.status(HttpStatus.CREATED).body(itensPokestops);
    }

    @PutMapping("{id}")
    public EntityModel<ItensPokestops> update (@PathVariable Long id, @RequestBody @Valid ItensPokestops itensPokestops){
        var itens = itensRepository.findById(itensPokestops.getItens().getId())
                .orElseThrow(()-> new RestNotFoundException("item not found"));

        var pokestops = pokestopsRepository.findById(itensPokestops.getPokestops().getId())
                .orElseThrow(()-> new RestNotFoundException("pokestop not found"));

        itensPokestopsRepository.findById(id).orElseThrow(()-> new RestNotFoundException("itensPokestops not found"));

        itensPokestops.setPokestops(pokestops);
        itensPokestops.setItens(itens);
        itensPokestops.setId(id);

        return itensPokestops.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ItensPokestops> destroy (@PathVariable Long id){
        var itensPokestops = itensPokestopsRepository.findById(id).orElseThrow(()-> new RestNotFoundException("itensPokestops not found"));

        itensPokestopsRepository.delete(itensPokestops);

        return ResponseEntity.noContent().build();
    }




}
