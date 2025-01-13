package com.proyecto.adopcion.service;


import com.proyecto.adopcion.model.Pet;
import com.proyecto.adopcion.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional(readOnly = true)
    public List<Pet> getAllPet() {
        return petRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Pet> getPetAvailable() {
        return petRepository.findPetByAvailable(true);
    }

    @Transactional
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional
    public Pet updatePet(Long id, Pet petUpdate) {
        Optional<Pet> petCurrent = petRepository.findById(id);

        if (petCurrent.isPresent()) {

            Pet pet = petCurrent.get();

            pet.setName(petUpdate.getName());
            pet.setAge(petUpdate.getAge());
            pet.setAvailable(petUpdate.getAvailable());
            pet.setTypePetId(petUpdate.getTypePetId());

            return petRepository.save(pet);

        } else {
            throw new RuntimeException("Mascota no encontada con el id: " + id);
        }
    }

    @Transactional
    public Pet parcialUpdatePet(Long id, Pet petUpdate) {
        Optional<Pet> petCurrent = petRepository.findById(id);

        if (petCurrent.isPresent()) {

            Pet pet = petCurrent.get();

            if (petUpdate.getName() != null) {
                pet.setName(petUpdate.getName());
            }
            if (petUpdate.getAge() != null) {
                pet.setAge(petUpdate.getAge());
            }
            if (petUpdate.getAvailable() != null) {
                pet.setAvailable(petUpdate.getAvailable());
            }
            if (petUpdate.getTypePetId() != null) {
                pet.setTypePetId(petUpdate.getTypePetId());
            }

            return petRepository.save(pet);

        } else {
            throw new RuntimeException("Mascota no encontrada con el id: " + id);
        }

    }

    @Transactional
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

}
