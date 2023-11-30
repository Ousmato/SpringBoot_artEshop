package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Entity.Produits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProduitRepository extends JpaRepository<Produits, Integer> {

List<Produits> findByCategories(Categories categories);

Produits findByCategoriesIdCategorieAndArtisansIdArtisans(int idcategorie, int idartisan);

Produits findByIdProduit(int idProduit);

List<Produits> findByPublier(boolean isPubilc);
List<Produits> findByCategoriesIdCategorieAndNomContaining(int idCategorie, String nomProduit);
//Produits findByNomAndidCat(String nomproduit, int idCategorie, int idArtisan);

}
