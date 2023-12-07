package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Types {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypes;

    @ManyToOne
    @JoinColumn(name = "idProduit",nullable = false)
    private Produits produits;

    @ManyToOne
    @JoinColumn(name = "idTaille",nullable = false)
    private Taille taille;

}
