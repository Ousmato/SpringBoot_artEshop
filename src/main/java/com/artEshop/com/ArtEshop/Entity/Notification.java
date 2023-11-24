package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotification;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String date;

    @OneToOne
    @JoinColumn(name = "idCommande")
    private Commandes commandes;

    @OneToOne
    @JoinColumn(name = "idProduit")
    private Produits produits;


    @ManyToOne
    @JoinColumn(name = "idArtisan",nullable = false)
    private  Artisans artisans;
}
