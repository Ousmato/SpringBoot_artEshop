package com.artEshop.com.ArtEshop.Controller;

import com.artEshop.com.ArtEshop.Entity.Admin;
import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Entity.User;
import com.artEshop.com.ArtEshop.Service.AdminService;
import com.artEshop.com.ArtEshop.Service.ServiceUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private AdminService adminService;

    //Endpoint pour ajouter un utilisateur
    @PostMapping("/add")
    @Operation(summary = "Création  d'un admin")
    public ResponseEntity<Admin> ajouterAdmin(

            @Valid @RequestParam("admin") String artisanString,
            @RequestParam(value ="photo", required=false) MultipartFile multipartFile) throws Exception {
        Admin admin ;
        try{
            admin = new JsonMapper().readValue(artisanString, Admin.class);
        }catch(JsonProcessingException e){
            throw new Exception(e.getMessage());
        }

        Admin saveAdmin = adminService.addAdmin(admin,multipartFile);

        return new ResponseEntity<>(saveAdmin, HttpStatus.OK);
    }
//    @PostMapping("/add")
//    public String add(@Valid @RequestBody Admin admin){
//        adminService.addAdmin(admin);
//        return "add successful";
//    }

    //Endpoint pour recuperer la liste des utilisateur
    @GetMapping("/list")
    public List<Admin> read(){
        return adminService.read();
    }

    //Endpoint pour suprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        adminService.delete(id);
        return "delete successful";
    }

    //Endpoint pour modifier les information d'un utilisateur
    @PutMapping("/modifier")
    public Admin modifier(@Valid @RequestBody Admin admin){
        return adminService.update(admin);
    }

    //Endpoint connexion pour verifier si l'utilisateur existe
    @PostMapping("/connexion")
    public ResponseEntity<Admin> verifyAdmin(@RequestParam("email") String email, @RequestParam("password") String password){
        return new ResponseEntity<>(adminService.connexion(email, password), HttpStatus.OK);
    }

}
