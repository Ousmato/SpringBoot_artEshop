package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.Admin;
import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Service.AdminService;
import com.artEshop.com.ArtEshop.Service.ArtisanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/artisan")

public class ArtisanController {
    @Autowired
    private ArtisanService artisanService;

    //Endpoint pour ajouter un utilisateur
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artisans > add(@Valid @RequestBody Artisans artisans) {
        try {
            Artisans addedArtisan = artisanService.addArtisan(artisans);
            return new ResponseEntity<>(addedArtisan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create")
    @Operation(summary = "Création  d'un artisan")
    public ResponseEntity<Artisans> ajouterArtisant(

            @Valid @RequestParam("artisan") String artisanString,
            @RequestParam(value ="photo", required=false) MultipartFile multipartFile) throws Exception {
        Artisans artisans ;
        try{
            artisans = new JsonMapper().readValue(artisanString, Artisans.class);
        }catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }

        Artisans saveArtisan = artisanService.addartisan(artisans,multipartFile);

        return new ResponseEntity<>(saveArtisan, HttpStatus.OK);
    }
//    @PostMapping("/add")
//    public String add(@Valid @RequestBody Artisans artisans){
//        artisanService.addArtisan(artisans);
//        return "add successful";
//    }

    //Endpoint pour recuperer la liste des utilisateur
    @GetMapping("/list")
    public List<Artisans> read(){
        return artisanService.read();
    }

    //Endpoint pour suprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        artisanService.delete(id);
        return "delete successful";
    }

    ////    :::::::::::::::::::::::::;methode pour activer artisan
    @PutMapping("/active")
    public ResponseEntity<Artisans> active(@Valid  @RequestBody Artisans artisans){

        return new ResponseEntity<>(artisanService.active(artisans),HttpStatus.OK);
    }

    //Endpoint connexion pour verifier si l'utilisateur existe
    @GetMapping("/connexion")
    public ResponseEntity<Artisans> artisan (@RequestParam("email") String email, @RequestParam("password") String password){
        return new ResponseEntity<>(artisanService.connexion(email, password), HttpStatus.OK);
    }


@PutMapping("/modifier")
public Artisans modifier(@Valid @RequestBody Artisans artisans){

        return artisanService.update(artisans);
}


}
