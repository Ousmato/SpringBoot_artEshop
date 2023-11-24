package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Produits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduit;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double prix;


    @Column
    private String culture;

    @Column (nullable = false)
    @NotBlank
    private String photo;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double quantite;

    @Column(nullable = false)
    private boolean publier = false;

    @Column(nullable = false)
    private boolean acheter = false;


    @ManyToOne
    @JoinColumn(name = "idArtisan",nullable = false)
    private Artisans artisans;

    @ManyToOne
    @JoinColumn(name = "idCategorie",nullable = false)
    private Categories categories;

}
