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
//::::::::::::::::ajouter commande
    @PostMapping("/ajouteCommande")
    public  ResponseEntity<Commandes> ajoutCommante(@RequestBody Commandes commandes){
        try {
            System.out.println("-----------------------------------------------------");
            System.out.println(commandes);
            System.out.println("-----------------------------------------------------1666");
            Commandes saveCommande = commandeService.ajouterCommande(commandes);
            return new ResponseEntity<>(saveCommande, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    :::::::::::::::list commande par artisan

//    :::::::::::::::::::::::::::::::;list produit par artisan
    @GetMapping("/artisanListproduit/{idArtisan}")
    private ResponseEntity<List<Produits>> artisanListProduit(@PathVariable int idArtisan){
        try {
            List<Produits> produitsList = produitService.artisanProduit(idArtisan);
            return new ResponseEntity<>(produitsList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    :::::::::::::::::::::::::::::;liste commande afficher dans panier sur interface
@GetMapping("/commandeParUtilisateur/{idUtilisateur}")
public  ResponseEntity<List<Commandes>> listCommandeUser(@PathVariable int idUtilisateur) {
    try {

        List<Commandes> list = commandeService.listCommandeUser(idUtilisateur);
        return new ResponseEntity<>(list,HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }
//    ::::::::::::::::::::::::;
@GetMapping("/achat/{idProduit}/{quantite}")
public  ResponseEntity<Produits> achat(@PathVariable int idProduit, @PathVariable double quantite){
    try {
        System.out.println("---------------contoller----------------");

        Produits acchat = produitService.achtat(idProduit,quantite);
        System.out.println("---------------produitSeervice----------------");

        return new ResponseEntity<>(acchat, HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//:::::::::::::::::::::::::::::::;ventes artisan
@GetMapping("/ventes/{idArtisan}")
private ResponseEntity<List<Commandes>> ventes(@PathVariable int idArtisan){
    try {
        List<Commandes> commandesList = commandeService.ventes(idArtisan);
        return new ResponseEntity<>(commandesList, HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//::::::::::::::::::::::::::::list de produits par categorie

    @GetMapping("/produitParCategori/{idCategorie}")
    private ResponseEntity<List<Produits>> produitCategorie(@PathVariable int idCategorie){
        try {
            List<Produits> produitsList = produitService.produitsListParCategori(idCategorie);
            return new ResponseEntity<>(produitsList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    ::::::::::::::: supprimer un produit par son id
    @GetMapping("/supprimer/{idProduit}")
    private ResponseEntity<String> supprimer(@PathVariable int idProduit){
        String produitSup = produitService.suprimerParId(idProduit);
        return new ResponseEntity<>(produitSup,HttpStatus.OK);
    }
//    :::::::::::::::modifier produit par son id
@GetMapping("/update/{idProduit}")
private ResponseEntity<Produits> modifier(@PathVariable int idProduit){
    try {
        Produits produits = produitService.updatProduit(idProduit);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}

