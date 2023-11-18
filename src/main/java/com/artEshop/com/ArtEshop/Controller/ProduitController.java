package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Entity.Produits;
import com.artEshop.com.ArtEshop.Service.ProduitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        @Valid @RequestParam("produit") String artisanString,
        @RequestParam(value ="photo", required=false) MultipartFile multipartFile) throws Exception {
    Produits produits ;
    try{
        produits = new JsonMapper().readValue(artisanString, Produits.class);
    }catch(JsonProcessingException e){
        throw new Exception(e.getMessage());
    }

    Produits saveProduit = produitService.addProduit(produits,multipartFile);

    return new ResponseEntity<>(saveProduit, HttpStatus.OK);
}
}
