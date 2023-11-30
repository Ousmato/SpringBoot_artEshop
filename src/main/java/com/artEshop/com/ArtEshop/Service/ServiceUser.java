package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Panier;
import com.artEshop.com.ArtEshop.Entity.User;
import com.artEshop.com.ArtEshop.Repository.PanierRepository;
import com.artEshop.com.ArtEshop.Repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceUser {
   @Autowired
 private UserRepository userRepository;
   @Autowired
   private PanierRepository panierRepository;
//methode post
public String adduser(User user) {
    User userExist = userRepository.findByEmail(user.getEmail());
    if (userExist != null) {
        throw new RuntimeException("User already exists");
    } else {

        LocalDate panierDate = LocalDate.now();
        System.out.println(panierDate+"je trouver date");
        // Créez et enregistrez d'abord le panier
        Panier panier = new Panier();

        panier.setDate(panierDate);
//        panier.set(user);
        panierRepository.save(panier);
        user.setPanier(panier);
        // Enregistrez ensuite l'utilisateur
        userRepository.save(user);
        return "success";
    }
   }
//   methode get
   public List<User> read(){
       return  userRepository.findAll();
   }
    //Methode pour supprimer un utilisateur
    public String delete(int id){
        userRepository.deleteById(id);
        return "suppression successful";
    }

    //Methode pour Modifier les informations d'un utilisateur
    public User update(User user){
        User userExist = userRepository.findByIdUser(user.getIdUser());
        if (userExist==null) {
            userRepository.save(user);
            return userRepository.findByIdUser(user.getIdUser());
        }else
            throw new RuntimeException("user exist");
    }

    //Methode connexion pour verifier si l'utilisateur existe
    public User connexion(String email, String password){
    System.out.println("je suis dedans");
        User user = userRepository.findByEmailAndPassword(email, password);
        System.out.println(user);

        if(user != null){
            return user;
        }else throw new EntityExistsException("user doesn't exist");
    }


//::::::::::::::::::::::::::nombre utilisateurs
    public Long nombreUser(){
    return userRepository.count();
    }

}
