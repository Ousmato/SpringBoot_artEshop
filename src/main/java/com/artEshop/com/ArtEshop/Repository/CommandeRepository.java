package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommandeRepository extends JpaRepository<Commandes,Integer> {
    Commandes findByProduitsIdProduitAndPanierIdPanier(int idProduit, int idPanier);
    Commandes findByIdCommande(int idCommande);
    List<Commandes> findByUtilisateurIdUser(int idUtilisateur);
    List<Commandes> findByProduitsArtisansIdArtisans(int idProduit);
}
