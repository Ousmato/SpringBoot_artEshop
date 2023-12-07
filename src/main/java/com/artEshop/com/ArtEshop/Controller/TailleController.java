package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.Categories;
import com.artEshop.com.ArtEshop.Entity.Taille;
import com.artEshop.com.ArtEshop.Service.CategorieService;
import com.artEshop.com.ArtEshop.Service.TailleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taille")
public class TailleController {

    @Autowired
    private TailleService tailleService;

    @PostMapping("/add")
    public ResponseEntity<Taille> add(@Valid @RequestBody Taille taille) {
        try {
            Taille addedTaille = tailleService.add(taille);
            return new ResponseEntity<>(addedTaille, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //    methode modifier
    @PutMapping("/modifier")
    public ResponseEntity<Taille> update(@Valid @RequestBody Taille taille) {
        try {
            Taille updatTaille = tailleService.update(taille);
            return new ResponseEntity<>(updatTaille, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //methode pour afficher les categories
    @GetMapping("/list")
    public ResponseEntity<List<Taille>> read() {
        try {
            List<Taille> readTaille = tailleService.read();
            return new ResponseEntity<>(readTaille, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
