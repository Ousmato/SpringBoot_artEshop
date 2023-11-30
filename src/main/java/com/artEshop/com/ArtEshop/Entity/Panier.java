package com.artEshop.com.ArtEshop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idPanier;

    @Column(nullable = false)
    private LocalDate date;

//   @OneToOne
//   @JoinColumn(name = "idUser",nullable = false)
//   private User user;
    @JsonIgnore
    @OneToMany
    private List<Commandes> commandes;


}
