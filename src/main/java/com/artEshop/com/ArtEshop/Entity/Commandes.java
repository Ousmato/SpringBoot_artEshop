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
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "idProduit",nullable = false)
    private Produits produits;

    @ManyToOne
    @JoinColumn(name = "idPanier",nullable = false)
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "idAchat",nullable = false)
    private Achat achat;

    @OneToOne
    @JoinColumn(name = "idNotification",nullable = false)
    private Notification notification;


}
