package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Entity.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoriRepository extends JpaRepository<Categories,Integer> {
   Categories findByNom(String nom);
   Categories findByIdCategorie(int idCategorie);
}
