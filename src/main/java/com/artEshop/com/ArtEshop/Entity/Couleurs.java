package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Couleurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCouleur;

    @Column(nullable = false)
    private String libelle;



}
