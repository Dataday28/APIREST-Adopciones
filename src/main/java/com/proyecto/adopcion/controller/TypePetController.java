package com.proyecto.adopcion.controller;

import com.proyecto.adopcion.model.TypePet;
import com.proyecto.adopcion.service.TypePetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipomascota")
public class TypePetController {

    @Autowired
    private TypePetService typePetService;

    @GetMapping
    public List<TypePet> getAllTypes() {
        return typePetService.getAllTypePets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypePet> getTypeById(@PathVariable Long id) {
        Optional<TypePet> typePet = typePetService.getTypePetById(id);
        return typePet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> createType(@RequestBody @Valid TypePet typePet, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        TypePet createType = typePetService.createTypePet(typePet);

        return ResponseEntity.ok(createType);
    }

}
