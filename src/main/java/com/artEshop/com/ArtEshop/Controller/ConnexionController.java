package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Service.ArtisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connexion")
public class ConnexionController {
    @Autowired
    private ArtisanService artisanService;

    //Endpoint connexion pour verifier si l'utilisateur existe
    @GetMapping("/verifier")
    public ResponseEntity<Object> verifierConnexion(@RequestParam("email") String email, @RequestParam("password") String password){
        return new ResponseEntity<>(artisanService.connexion(email, password), HttpStatus.OK);
    }
}
