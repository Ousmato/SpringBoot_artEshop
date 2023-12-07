package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Produits;
import com.artEshop.com.ArtEshop.Entity.Taille;
import com.artEshop.com.ArtEshop.Entity.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Types, Integer> {
    List<Types> findByTaille(Taille taille);
  List<Types> findByProduitsIdProduit(int idProduit);
//  Types findByProduitsIdProduit(int idpro)
}
