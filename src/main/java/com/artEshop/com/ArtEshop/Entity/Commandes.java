package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Commandes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommande;

    @Column(nullable = false)
    private double quantite;

    @ManyToOne
    @JoinColumn(name = "idProduit",nullable = false)
    private Produits produits;

    @ManyToOne
    @JoinColumn(name = "idPanier",nullable = false)
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "idTaille",nullable = false)
    private Taille taille;

    @OneToOne
    @JoinColumn(name = "idNotification",nullable = false)
    private Couleurs couleurs;


}
