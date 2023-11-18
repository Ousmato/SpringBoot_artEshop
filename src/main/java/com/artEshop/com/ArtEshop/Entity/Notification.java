package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotification;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "idArtisan",nullable = false)
    private  Artisans artisans;
}
