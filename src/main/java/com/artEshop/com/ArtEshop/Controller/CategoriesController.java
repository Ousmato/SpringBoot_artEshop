package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Entity.CategoryWithProduit;
import com.artEshop.com.ArtEshop.Entity.Produits;
import com.artEshop.com.ArtEshop.Entity.User;
import com.artEshop.com.ArtEshop.Service.CategorieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")

public class CategoriesController {

    @Autowired
    private CategorieService categorieService;

    @PostMapping("/add")
    public ResponseEntity<Categories> add(@Valid @RequestBody Categories categories) {
        try {
            Categories addedCategory = categorieService.add(categories);
            return new ResponseEntity<>(addedCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    methode modifier
@PutMapping("/modifier")
public ResponseEntity<Categories> update(@Valid @RequestBody Categories categories) {
    try {
        Categories updatCategory = categorieService.update(categories);
        return new ResponseEntity<>(updatCategory, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

//methode pour afficher les categories
@GetMapping("/listCategorie")
public ResponseEntity<List<CategoryWithProduit>> read() {
    try {
        List<CategoryWithProduit> readCategory = categorieService.read();
        return new ResponseEntity<>(readCategory, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

//:::::::::::::::::::::::list categorie mobil
@GetMapping("/list")
public ResponseEntity<List<Categories>> readMobilAll() {
    try {
        List<Categories> readCategory = categorieService.readMobilCategorie();
        return new ResponseEntity<>(readCategory, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
    }
//    ::::::::::::::::::
@GetMapping("/supprimer/{idCategorie}")
private ResponseEntity<String> modifier(@PathVariable int idCategorie){
    try {
        String categoriesSupprimer = categorieService.delete(idCategorie);
        return new ResponseEntity<>(categoriesSupprimer, HttpStatus.OK);
    }catch (Exception e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
