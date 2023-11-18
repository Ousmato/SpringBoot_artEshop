package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
    private int prix;


    @Column
    private String culture;

    @Column (nullable = false)
    @NotBlank
    private String photo;


    @ManyToOne
    @JoinColumn(name = "idArtisan",nullable = false)
    private Artisans artisans;

    @ManyToOne
    @JoinColumn(name = "idCategorie",nullable = false)
    private Categories categories;

//    @OneToMany
//    private List<Commandes> commandes;


}
