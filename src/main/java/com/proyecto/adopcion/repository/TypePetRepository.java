package com.proyecto.adopcion.repository;

import com.proyecto.adopcion.model.TypePet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePetRepository extends JpaRepository<TypePet, Long> {
}
