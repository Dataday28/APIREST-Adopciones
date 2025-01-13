package com.proyecto.adopcion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotNull(message = "El campo no debe estar vacio")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypePet typePetId;

    @NotNull(message = "El campo no debe estar vacio")
    private Boolean available;

}
