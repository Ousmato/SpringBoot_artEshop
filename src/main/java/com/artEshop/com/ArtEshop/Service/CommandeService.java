package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.*;
import com.artEshop.com.ArtEshop.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ArtisanRepository artisanRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private  NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TailleRepository tailleRepository;

    public Commandes ajouterCommande( Commandes commandes){
        System.out.println("------entre-----");
        Produits produits = produitRepository.findByIdProduit(commandes.getProduits().getIdProduit());
             if (produits!= null){
                 DateTimeFormatter dateCommadeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
                 System.out.println("yyyy/MM/dd hh:mm:ss-> " + dateCommadeFormatter.format(LocalDateTime.now()));
                 String dateCommande = dateCommadeFormatter.format(LocalDateTime.now());
                 commandes.setDate(dateCommande);
                Commandes commandes1 = commandeRepository.save(commandes);
                notificationService.messageArtisan(commandes1.getIdCommande());
            return commandes1;
            }

       throw new RuntimeException("produit exist in comande");
    }


//    ::::::::::::::::list de commande pour un user
public List<Commandes> listCommandeUser(int idUtilisateur){
    User userExist = userRepository.findByIdUser(idUtilisateur);
    List<Commandes> commandesList = commandeRepository.findByUtilisateurIdUser(idUtilisateur);
    if (userExist!=null){
        return commandesList;
    }
    throw new RuntimeException("artisan no exist");
}
//:::::::::::::::;list commande pour un artisan
//    :::::::::::::::::::;les commande des produit correspond aux vente d'un artisan

    public List<Commandes> ventes(int idArtisan){
        Artisans artisanExist = artisanRepository.findByIdArtisans(idArtisan);
        List<Commandes> commandesList = commandeRepository.findByProduitsArtisansIdArtisans(idArtisan);
        if (artisanExist!=null){
            return commandesList;
        }
        throw new RuntimeException("artisan no exist");
    }
}





