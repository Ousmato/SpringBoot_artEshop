package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAchat;

//    @OneToMany
//    private List<Commandes> commandes;
    @ManyToOne
    @JoinColumn(name = "idCommande", nullable = false)
    private Commandes commandes;
}
