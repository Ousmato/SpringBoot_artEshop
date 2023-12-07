package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.User;
import com.artEshop.com.ArtEshop.Service.ServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ServiceUser userService;

    //Endpoint pour ajouter un utilisateur
    @PostMapping("/add")
    public User add(@Valid @RequestBody User user){
       return userService.adduser(user);

    }

    //Endpoint pour recuperer la liste des utilisateur
    @GetMapping("/list")
    public List<User> read(){
        return userService.read();
    }

    //Endpoint pour suprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        userService.delete(id);
        return "delete successful";
    }

    //Endpoint pour modifier les information d'un utilisateur
    @PutMapping("/modifier")
    public User modifier(@Valid @RequestBody User user){
        return userService.update(user);
    }

    //Endpoint connexion pour verifier si l'utilisateur existe
    @GetMapping("/connexion")
    public ResponseEntity<User> user(@RequestParam("email") String email, @RequestParam("password") String password){
        return new ResponseEntity<>(userService.connexion(email, password), HttpStatus.OK);
    }
//:::::::::::::::::::::
    @GetMapping("/nombre")
    public Long nomreUser(){
        return userService.nombreUser();
    }

}
