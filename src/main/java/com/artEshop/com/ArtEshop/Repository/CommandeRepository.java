package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CommandeRepository extends JpaRepository<Commandes,Integer> {
    Commandes findByProduitsIdProduitAndPanierIdPanier(int idProduit, int idPanier);
}
