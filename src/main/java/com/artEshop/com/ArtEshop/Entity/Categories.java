package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;

    @Column(nullable = false)
    @NotBlank
    private String nom;

    @OneToMany
    private List<Produits> produits;


}
