package com.proyecto.adopcion.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name = "adoptions")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = Pet.class)
    @JoinColumn(name = "pet_id")
    private Pet petId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "adoption_Date")
    private Date adoptionDate;

}
