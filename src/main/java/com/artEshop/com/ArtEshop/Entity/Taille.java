package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Taille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTaille;

    @Column(nullable = false)
    @NotBlank
    private String libelle;


}
