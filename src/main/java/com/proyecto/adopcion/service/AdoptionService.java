package com.proyecto.adopcion.service;

import com.proyecto.adopcion.model.Adoption;
import com.proyecto.adopcion.model.Pet;
import com.proyecto.adopcion.repository.AdoptionRepository;
import com.proyecto.adopcion.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final PetRepository petRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, PetRepository petRepository) {
        this.adoptionRepository = adoptionRepository;
        this.petRepository = petRepository;
    }


    @Transactional(readOnly = true)
    public Optional<Adoption> getAdoptionById(Long id) {
        return adoptionRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Adoption> getAllAdoptions() {
        return adoptionRepository.findAll();
    }

    @Transactional
    public Adoption createAdoption(Adoption adoption) {

        if (adoption == null || adoption.getPetId() == null) {
            throw new IllegalArgumentException("La adopcion o el id de la mascota no pueden ser nulos");
        }

        Long petId = adoption.getPetId().getId();
        Pet pet = petRepository.findById(petId)
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro la mascota con el id: " +petId));

        if (!pet.getAvailable()) {
            throw new IllegalStateException("La mascota no esta disponible para la adopcion");
        }

        pet.setAvailable(false);
        petRepository.save(pet);

        return adoptionRepository.save(adoption);
    }

    
}
