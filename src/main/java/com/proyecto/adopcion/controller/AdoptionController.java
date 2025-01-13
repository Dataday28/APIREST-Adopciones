package com.proyecto.adopcion.controller;

import com.proyecto.adopcion.model.Adoption;
import com.proyecto.adopcion.service.AdoptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adopciones")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;


    @GetMapping
    public List<Adoption> getAllAdoptions() {
        return adoptionService.getAllAdoptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adoption> getAdoptionById(@PathVariable Long id) {
        Optional<Adoption> adoption = adoptionService.getAdoptionById(id);
        return adoption.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Object> createAdoption(@RequestBody @Valid Adoption adoption, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Adoption createAdoption = adoptionService.createAdoption(adoption);

        return ResponseEntity.ok(createAdoption);

    }

}
