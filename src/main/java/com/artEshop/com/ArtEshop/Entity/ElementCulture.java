package com.artEshop.com.ArtEshop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ElementCulture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idElementCulture;

    @Column(nullable = false)
    private String descriptionElement;

    @ManyToOne
    @JoinColumn(name = "idCulture", nullable = false)
    private Cultures cultures;

}
