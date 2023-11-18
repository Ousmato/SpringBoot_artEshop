package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Admin;
import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Entity.Produits;
import com.artEshop.com.ArtEshop.Repository.CategoriRepository;
import com.artEshop.com.ArtEshop.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategorieService {

    @Autowired
    private CategoriRepository categoriRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public Categories add(Categories categories){
        Categories categoriExist = categoriRepository.findByNom(categories.getNom());
        if(categoriExist!=null){
            throw new RuntimeException(" Cette categorie existe deja creer une autre");

        }else {
            return categoriRepository.save(categories);
        }
    }
    // methode pour modifier une categorie
    public Categories update(Categories categories){
        Categories categoriExist = categoriRepository.findByIdCategorie(categories.getIdCategorie());
        if (categoriExist==null) {
            throw new RuntimeException("Cette catégorie n'existe pas.");
        }
            List<Produits> produitsExist = produitRepository.findByCategories(categories);
            if (!produitsExist.isEmpty()) {
                throw new RuntimeException("Cette catégorie ne peut pas être modifiée car des produits y sont associés.");

            }
            return categoriRepository.save(categories);
//            return categoriRepository.findByIdAdmin(admin.getIdAdmin());
        }

//        methode pour supprimer
public String delete(int idcategorie) {
    Categories categoryExist = categoriRepository.findByIdCategorie(idcategorie);
    if (categoryExist != null) {
        List<Produits> produitsExist = produitRepository.findByCategories(categoryExist);
        if (!produitsExist.isEmpty()) {
            return "Cette catégorie ne peut pas être supprimée car des produits y sont associés.";
        }
        categoriRepository.deleteById(idcategorie);
        return "Suppression réussie";
    }

    return "La catégorie n'existe pas, aucune suppression nécessaire.";
}


//methode list categories
    public List<Categories> read(){
        return categoriRepository.findAll();
    }
}
