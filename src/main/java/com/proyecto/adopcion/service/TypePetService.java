package com.proyecto.adopcion.service;

import com.proyecto.adopcion.model.TypePet;
import com.proyecto.adopcion.repository.TypePetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypePetService {

    private final TypePetRepository typePetRepository;

    public TypePetService(TypePetRepository typePetRepository) {
        this.typePetRepository = typePetRepository;
    }

    @Transactional(readOnly = true)
    public Optional<TypePet> getTypePetById(Long id) {
        return typePetRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<TypePet> getAllTypePets() {
        return typePetRepository.findAll();
    }

    @Transactional
    public TypePet createTypePet(TypePet typePet) {
        return typePetRepository.save(typePet);
    }

}
