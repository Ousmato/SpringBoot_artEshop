package com.artEshop.com.ArtEshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String pays;

    @Column(nullable = false)
    private String adresse;


    @OneToOne
    @JoinColumn(name = "idPanier")
    private Panier panier;

    @JsonIgnore
    @OneToMany()
    private List<Commandes> commandesList;


}
