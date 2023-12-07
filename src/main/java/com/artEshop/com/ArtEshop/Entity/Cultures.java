package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cultures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCulture;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String regionCulturel;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String ethnie;

//    @OneToMany
//    private List<ElementCulture> elementCultures;

    @ManyToOne
    @JoinColumn(name = "idAdmin", nullable = false)
    private Admin admin;


}
