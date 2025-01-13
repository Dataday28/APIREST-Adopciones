package com.proyecto.adopcion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo no debe estar vacio")
    private String name;

    @NotNull(message = "El campo no debe estar vacio")
    private String email;

    @NotNull(message = "El campo no debe estar vacio")
    private String phone;

}
