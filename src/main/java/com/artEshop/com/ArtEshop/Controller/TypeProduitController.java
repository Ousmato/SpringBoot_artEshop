package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.Types;
import com.artEshop.com.ArtEshop.Service.TypeProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/typeProduits")
public class TypeProduitController {
    @Autowired
    private TypeProduitService typeProduitService;

    @GetMapping("/list")
    public ResponseEntity<List<Types>> read(){
        try {
            List<Types> typesList = typeProduitService.readallProduitType();
            return new ResponseEntity<>(typesList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
