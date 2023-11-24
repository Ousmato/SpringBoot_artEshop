package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Produit_Color;
import com.artEshop.com.ArtEshop.Entity.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Produit_ColorRepository extends JpaRepository<Produit_Color, Integer> {
Produit_Color findByProduits(Produits produits);
}
