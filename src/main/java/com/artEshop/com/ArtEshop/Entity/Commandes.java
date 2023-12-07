package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Commandes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommande;

    @Column(nullable = false)
    private double quantite;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "idProduit",nullable = false)
    private Produits produits;

    @ManyToOne
    @JoinColumn(name = "idPanier",nullable = false)
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "idUsers",nullable = false)
    private User utilisateur;

    @OneToOne
    @JoinColumn(name = "idTaille",nullable = false)
    private Taille tailleProduit;

    @OneToOne
    @JoinColumn(name = "idCouleur",nullable = false)
    private Couleurs couleursProduit;


}
