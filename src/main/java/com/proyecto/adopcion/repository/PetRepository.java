package com.proyecto.adopcion.repository;

import com.proyecto.adopcion.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findPetByAvailable(boolean available);

}
