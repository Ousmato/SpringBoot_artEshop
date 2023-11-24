package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.*;
import com.artEshop.com.ArtEshop.Service.ProduitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/produit")

public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping("/added")
    public ResponseEntity<Produits> add(@Valid @RequestBody Produits produits) {
        try {
            Produits addedProduit = produitService.add(produits);
            return new ResponseEntity<>(addedProduit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    ::::::::::::::::::::::::::
@PostMapping("/add")
@Operation(summary = "ajoutez un produit")
public ResponseEntity<Produits> ajouterProduit(
@RequestParam("couleur") String couleurProduit,@RequestParam("taille") String tailleProduit,
        @Valid @RequestParam("produit") String produitString,
        @RequestParam(value ="photo", required=false) MultipartFile multipartFile) throws Exception {
    Produits produits ;

    List<Taille> tailles = new ArrayList<>();
    List<Couleurs> couleurs = new ArrayList<>();
    try{
        produits = new JsonMapper().readValue(produitString, Produits.class);
        tailles = new JsonMapper().readValue(tailleProduit, new TypeReference<List<Taille>>() {});
        couleurs = new JsonMapper().readValue(couleurProduit, new TypeReference<List<Couleurs>>() {
        });
        System.out.println(couleurs.toString());
        System.out.println(tailles.toString());
        System.out.println("---------------------taille to string----");
    }catch(JsonProcessingException e){
        throw new Exception(e.getMessage());
    }



    // Divisez la chaîne de taille en une liste de tailles (supposant que la change est séparée par des virgules)
     //convertirStringEnListeDeTailles(tailleProduit);
//    List<Couleurs> couleurs = convertirStringEnListeDeCouleur(couleurProduit);

    Produits saveProduit = produitService.addProduit(tailles,produits,multipartFile,couleurs);

    return new ResponseEntity<>(saveProduit, HttpStatus.OK);
}
//    private List<Taille> convertirStringEnListeDeTailles(String tailleString) {
//        List<Taille> tailles = new ArrayList<>();
//        // Implémentez ici votre logique pour convertir la chaîne de tailles en une liste de Taille
//        String[] taillesArray = tailleString.split(",");
//        for (String taille : taillesArray) {
//            Taille nouvelleTaille = new Taille();
//            nouvelleTaille.setLibelle(taille.trim()); // Assurez-vous de définir correctement les attributs de la classe Taille
//            tailles.add(nouvelleTaille);
//        }
//        return tailles;
//    }
//    private List<Couleurs> convertirStringEnListeDeCouleur(String couleurString) {
//        List<Couleurs> couleurs = new ArrayList<>();
//        // Implémentez ici votre logique pour convertir la chaîne de tailles en une liste de Taille
//        // Par exemple, vous pouvez diviser la chaîne par une virgule et créer des objets Taille correspondants
//        // Exemple hypothétique :
//        String[] couleursArray = couleurString.split(",");
//        for (String couleur : couleursArray) {
//            Couleurs nouvelleCouleur = new Couleurs();
//            nouvelleCouleur.setLibelle(couleur.trim()); // Assurez-vous de définir correctement les attributs de la classe Taille
//            couleurs.add(nouvelleCouleur);
//        }
//        return couleurs;
//}
}
