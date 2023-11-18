package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Taille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTaille;

    @Column(nullable = false)
    private String libelle;


}
