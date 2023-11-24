package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produit_Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduitColor;

    @ManyToOne
    @JoinColumn(name = "idProduit",nullable = false)
    private Produits produits;

    @ManyToOne
    @JoinColumn(name = "idCouleurs",nullable = false)
    private Couleurs couleurs;
}
