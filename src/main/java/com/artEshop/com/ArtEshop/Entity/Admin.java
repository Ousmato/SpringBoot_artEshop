package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdmin;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String sexe;

//    @OneToMany
//    private List<Cultures> culturesList;

//
//    @OneToMany
//    private List<Artisans> artisansList;


}
