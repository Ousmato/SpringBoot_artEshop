package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Produit_Color;
import com.artEshop.com.ArtEshop.Entity.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface Produit_ColorRepository extends JpaRepository<Produit_Color, Integer> {
Produit_Color findByProduits(Produits produits);
List<Produit_Color> findByProduitsIdProduit(int idProduit);
}
