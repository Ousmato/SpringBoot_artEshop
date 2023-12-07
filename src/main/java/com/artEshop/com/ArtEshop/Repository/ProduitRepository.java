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

Produits findByIdProduitAndEffacerProduit(int idProduit, boolean isDelete );
Produits findByIdProduit(int idProduit);

List<Produits> findByPublierAndEffacerProduit(boolean isPubilc, boolean isEffacer);
List<Produits> findByCategoriesIdCategorieAndNomContaining(int idCategorie, String nomProduit);
//Produits findByNomAndidCat(String nomproduit, int idCategorie, int idArtisan);
    List<Produits> findByArtisansIdArtisans(int idArtisan);

    List<Produits> findByArtisansIdArtisansAndAcheter(int idArtisan ,boolean achateter);

    List<Produits> findByCategoriesIdCategorieAndPublier(int idCategorie, boolean isPublic);

//   Produits findByArtisansIdArtisans(int idArtisan);
    int countByCategoriesIdCategorieAndEffacerProduit(int idProduit, boolean isDelete);

    List<Produits> findByArtisansIdArtisansAndEffacerProduit(int idArtisan, boolean isDelete);

}
