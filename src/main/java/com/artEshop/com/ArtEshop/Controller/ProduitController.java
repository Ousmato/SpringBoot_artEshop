package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.*;
import com.artEshop.com.ArtEshop.Repository.CommandeRepository;
import com.artEshop.com.ArtEshop.Service.CommandeService;
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
import java.util.Map;

@RestController
@RequestMapping("/produit")

public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private CommandeService commandeService;

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


    Produits saveProduit = produitService.addProduit(tailles,produits,multipartFile,couleurs);

    return new ResponseEntity<>(saveProduit, HttpStatus.OK);
    }
//    :::::::::::produit avec information complet
    @PostMapping("/produitList/{idProduit}")
    private ResponseEntity<Map<String, Object>> readAllproduitInformation(@PathVariable int idProduit){
        try {
            Map<String, Object> produitInfoComplet = produitService.produitByTaileAndColor(idProduit);
            return new ResponseEntity<>(produitInfoComplet,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    :::::::::::::::publier produit
    @PostMapping("/publier/{idProduit}")
    private ResponseEntity<Produits> produitPubier(@PathVariable int idProduit){
        try {
            Produits produit = produitService.publier(idProduit);
            return new ResponseEntity<>(produit,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//      :::::::::::::::rejeter produit
    @DeleteMapping("/rejeter/{idProduit}")
    private ResponseEntity<String> produitRejeter(@PathVariable int idProduit){
        try {
            String produit = produitService.rejeter(idProduit);
            return new ResponseEntity<>(produit,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    :::::::::::::::::::::list produit publier
    @GetMapping("/listproduitProduitPublier")
    public ResponseEntity<List<Produits>> readAll(){
        try{
            List<Produits> list = produitService.getProduitsList();
            return new ResponseEntity<>(list,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    :::::::::::::list produit simillaire avec nom et category
    @GetMapping("/produitSimilaire/{idCategore}/{nomProduit}")
    private ResponseEntity<List<Produits>> listProduitSimilaire(@PathVariable int idCategore, @PathVariable String nomProduit){
        try {
            List<Produits> produitsList = produitService.produitsListMobile(idCategore,nomProduit);
            return new ResponseEntity<>(produitsList, HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
//    :::::::::: ajouter produit au commende
//    @GetMapping("/ajouteCommande")
//    private ResponseEntity<Commandes> ajoutCommande(
//            @RequestParam double quantite,
//            @RequestParam ("produit") String produitSring,
//            @RequestParam("user") String userString,
//            @RequestParam("panier") String panierString,
//            @RequestParam("taille") String tailleString,
//            @RequestParam("couleur") String couleurString
//
//    ){
//        Produits produits;
//        User user;
//        Panier panier;
//        Taille taille;
//        Couleurs couleurs;
//        try {
//
//            produits = new JsonMapper().readValue(produitSring, Produits.class);
//            user = new JsonMapper().readValue(userString, User.class);
//            panier = new JsonMapper().readValue(panierString, Panier.class);
//            taille = new JsonMapper().readValue(tailleString, Taille.class);
//            couleurs = new JsonMapper().readValue(couleurString, Couleurs.class);
//
//            Commandes saveCommande = commandeService.ajouterCommande(produits,quantite,user,panier,taille,couleurs);
//            return new ResponseEntity<>(saveCommande, HttpStatus.OK);
//        }catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PostMapping("/ajouteCommande")
//    private ResponseEntity<Commandes> ajouteCommande(
//            @RequestParam ("commande") String commandeString){
//        Commandes commandes;
//        try {
//            commandes = new JsonMapper().readValue(commandeString, Commandes.class);
//            Commandes saveCommande = commandeService.ajouterCommande(commandes);
//            return new ResponseEntity<>(saveCommande, HttpStatus.OK);
//
//    }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//}

    @PostMapping("/ajouteCommande")
    public  ResponseEntity<Commandes> ajoutCommante(@RequestBody Commandes commandes){
        try {
            System.out.println("-----------------------------------------------------");
            Commandes saveCommande = commandeService.ajouterCommande(commandes);
            System.out.println("-----------------------------------------------------1666");
            return new ResponseEntity<>(saveCommande, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

