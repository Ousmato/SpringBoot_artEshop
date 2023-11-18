package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Entity.Produits;
import com.artEshop.com.ArtEshop.Entity.User;
import com.artEshop.com.ArtEshop.Repository.ArtisanRepository;
import com.artEshop.com.ArtEshop.Repository.ProduitRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service

public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ArtisanRepository artisanRepository;

    public Produits add(Produits produits) {
        Produits produitExist = produitRepository.findByCategoriesIdCategorieAndArtisansIdArtisans(produits.getCategories().getIdCategorie(), produits.getArtisans().getIdArtisans());

        if (produitExist != null) {
            if (!produitExist.getArtisans().equals(produits.getArtisans())) {
                // L'artisan est différent, vous pouvez enregistrer le produit
                produitRepository.save(produits);
            } else {
                // Même artisan et même catégorie, empêchez l'enregistrement
                throw new RuntimeException("Un produit avec la même catégorie et le même artisan existe déjà.");
            }
        } else {
            // Aucun produit similaire trouvé, enregistrez le produit
            produitRepository.save(produits);
        }

        return produits;
    }

//    :::::::::::::::::::::::::::::

    //::::::::::::::::::::::::::::::::::::::
    public Produits addProduit(Produits produits, MultipartFile multipartFile) throws Exception {
        if (produitRepository.findByCategoriesIdCategorieAndArtisansIdArtisans(produits.getCategories().getIdCategorie(),produits.getArtisans().getIdArtisans()) == null) {

            if (multipartFile != null) {
                String location = "C:\\xampp\\htdocs\\artImage";
                try {
                    Path rootlocation = Paths.get(location);
                    if (!Files.exists(rootlocation)) {
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                        produits.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                    } else {
                        try {
                            String nom = location + "\\" + multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if (!Files.exists(name)) {
                                Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                                produits.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                            } else {
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                                produits.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                            }
                        } catch (Exception e) {
                            throw new Exception("some error");
                        }
                    }
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }

            return produitRepository.save(produits);
        } else {
            throw new EntityExistsException("cet produit exist deja");
        }
    }
}
