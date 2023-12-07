package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Entity.Produits;
import com.artEshop.com.ArtEshop.Entity.Taille;
import com.artEshop.com.ArtEshop.Entity.Types;
import com.artEshop.com.ArtEshop.Repository.TailleRepository;
import com.artEshop.com.ArtEshop.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TailleService {
    @Autowired
    private TailleRepository tailleRepository;

    @Autowired
    private TypeRepository typeRepository;

    public Taille add(Taille taille){
        Taille tailExist = tailleRepository.findByLibelle(taille.getLibelle());
        if(tailExist!=null){
            throw new RuntimeException(" Cette Taille existe deja creer une autre");

        }else {
            return tailleRepository.save(taille);
        }
    }

    // methode pour modifier une categorie
    public Taille update(Taille taille){
        Taille tailleExist = tailleRepository.findByLibelle(taille.getLibelle());
        if (tailleExist==null) {
            throw new RuntimeException("Cette Taille n'existe pas.");
        }
        List<Types> typeExist = typeRepository.findByTaille(taille);
        if (!typeExist.isEmpty()) {
            throw new RuntimeException("Cette Taille ne peut pas être modifiée car des produits y sont associés.");

        }
        return tailleRepository.save(taille);
//            return categoriRepository.findByIdAdmin(admin.getIdAdmin());
    }

    //        methode pour supprimer
    public String delete(int idTaille) {
        Taille tailleExist = tailleRepository.findByIdTaille(idTaille);
        if (tailleExist != null) {
            List<Types> typesExist = typeRepository.findByTaille(tailleExist);
            if (!typesExist.isEmpty()) {
                return "Cette taille ne peut pas être supprimée car des produits y sont associés.";
            }
            tailleRepository.deleteById(idTaille);
            return "Suppression réussie";
        }

        return "La catégorie n'existe pas, aucune suppression nécessaire.";
    }


    //methode list categories
    public List<Taille> read(){
        return tailleRepository.findAll();
    }
}
