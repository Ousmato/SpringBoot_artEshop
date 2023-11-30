package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.*;
import com.artEshop.com.ArtEshop.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private PanierRepository panierRepository;
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
        System.out.println("------------------entree-----------------------------------");
        Produits produits = produitRepository.findByIdProduit(commandes.getProduits().getIdProduit());
//        Taille taille = tailleRepository.findByIdTaille(commandes.getTaille().getIdTaille());
//        System.out.println(taille);
        if (produits!= null){
            System.out.println("---------------------produit trover--------------------------------");
//            User userExist = userRepository.findByIdUser(commandes.getUser().getIdUser());
//            System.out.println(userExist.toString());
//            if (userExist != null){
            System.out.println(commandes.getProduits());
            System.out.println(commandes.getCouleurs());
            System.out.println(commandes.getQuantite());
            System.out.println(commandes.getTaille());
            System.out.println(commandes.getPanier());
            System.out.println(commandes.getUser());
            System.out.println("---------------------terminer------------------");;
                System.out.println("------------user avec-----------------------------------------");
                commandeRepository.save(commandes);
                System.out.println("-----------------commande save------------------------------------");
                Notification notification = new Notification();
                notification.setProduits(commandes.getProduits());
                notification.setCommandes(commandes);
                System.out.println("------------------------notification-----------------------------");
                notificationService.messageArtisan(notification);

                notificationRepository.save(notification);
                System.out.println("---------------ind--------------------------------------");
            }
//        }
        throw new RuntimeException("produit exist in comande");
    }

}

//        if (produits!=null){
//            Types types = typeRepository.findByProduitsIdProduit(idProduit);
//
//            Panier panier = panierRepository.findByUserIdUser(commandes.getUser().getIdUser());
//            Taille taille = tailleRepository.findByIdTaille(commandes.getTaille().getIdTaille());
//            Taille taille2 = commandes.getTaille();
//            Couleurs couleurs = couleurRepositorie.findByIdCouleur(commandes.getCouleurs().getIdCouleur());
//            Commandes commandes = new Commandes();
//            commandes.setPanier(panier);
//            commandes.setProduits(produits);
//            commandes.setQuantite(quantite);
//            commandes.setUser(user);
//            commandes.setTaille(taille);
//            commandes.setCouleurs(couleurs);






