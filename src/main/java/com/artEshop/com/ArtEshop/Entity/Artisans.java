package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Entity
public class Artisans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArtisans;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String nom;


    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String sexe;

    @Column(nullable = false)
    private String entreprise;

    @Column(nullable = false)
    private String description;
//    @Column(columnDefinition = "active")
    private boolean active = false;

//    @ManyToOne
//    @JoinColumn(name = "idAdmin")

    private  int  idAdmin;
//
//    public void activerArtisan() {
//        this.active = true;
//    }

}
