package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.*;
import com.artEshop.com.ArtEshop.Repository.*;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ArtisanRepository artisanRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private Produit_ColorRepository produit_colorRepository;

    @Autowired
    private CouleurRepositorie couleurRepositorie;

    @Autowired
    private  NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public Produits add(Produits produits) {
        Produits produitExist = produitRepository.findByCategoriesIdCategorieAndArtisansIdArtisans(produits.getCategories().getIdCategorie(), produits.getArtisans().getIdArtisans());
        Types types = new Types();
        if (produitExist != null) {
            if (!produitExist.getArtisans().equals(produits.getArtisans())) {
                // L'artisan est différent, vous pouvez enregistrer le produit
                produitRepository.save(produits);
            } else {
                // Même artisan et même catégorie, empêchez l'enregistrement
                throw new RuntimeException("Un produit avec la même catégorie et le même artisan existe déjà.");
            }
        } else {
            // Aucun produit similaire trouvé, enregistrez le produit
            produitRepository.save(produits);
        }

        return produits;
    }

//    :::::::::::::::::::::::::::::

    //::::::::::::::::::::::::::::::::::::::
    public Produits addProduit(List<Taille> tailles,Produits produits, MultipartFile multipartFile, List<Couleurs> couleursProduit) throws Exception {
        if (produitRepository.findByCategoriesIdCategorieAndArtisansIdArtisans(produits.getCategories().getIdCategorie(),produits.getArtisans().getIdArtisans()) == null) {

            System.out.println("taille.toString()");
            if (multipartFile != null) {
                String location = "C:\\xampp\\htdocs\\artImage";
                try {
                    Path rootlocation = Paths.get(location);
                    if (!Files.exists(rootlocation)) {
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                        produits.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                    } else {
                        try {
                            String nom = location + "\\" + multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if (!Files.exists(name)) {
                                Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                                produits.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                            } else {
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                                produits.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                            }
                        } catch (Exception e) {
                            throw new Exception("some error");
                        }
                    }
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
//            ::::::::::::::la date d'enregistrement du produit

            LocalDate dateproduit = LocalDate.now();
            produits.setDate(dateproduit);
//            produits.setCategories(produits.getCategories());

            Produits newProduit = produitRepository.save(produits);
//            ::::::::::enregistrement automatique du type de produits

            // Enregistrement automatique des associations produit-taille
            for (Taille taille : tailles) {
                Types types = new Types();
                types.setProduits(newProduit);

                System.out.println("ousmato---------------------");
                System.out.println(taille.toString());
                types.setTaille(taille);
                typeRepository.save(types);
                System.out.println("ousmato---------------------");
            }


            for (Couleurs couleur : couleursProduit) {
                couleurRepositorie.save(couleur);
                Produit_Color produitColor = new Produit_Color();
                produitColor.setProduits(newProduit);
                produitColor.setCouleurs(couleur);
                produit_colorRepository.save(produitColor);
            }

//::::::::::::::::enregistrement de notification
            Notification notification = new Notification();
            notification.setProduits(newProduit);
            notification.setArtisans(newProduit.getArtisans());
            Notification notification1 = notificationService.messageAdmin(notification);
            notificationRepository.save(notification1);
            return newProduit;
        } else {
            throw new EntityExistsException("cet produit exist deja");
        }
    }

//    ::::::::::::::::;;methode pour appel list
    public  List<Produits> readallProduit(){
        return produitRepository.findAll();
    }

    public List<Produits> getProduitsList(){

        List<Produits> produitsList = produitRepository.findByPublier(true);
        return  produitsList;
    }

    //    :::::::::::::;apelle produit avec couleur et taille
public   Map<String, Object>  produitByTaileAndColor(int idproduit){
        Produits produitsExist = produitRepository.findByIdProduit(idproduit);
    Map<String, Object> infoProduit = new HashMap<>();

        if (produitsExist!=null){
            List<Types> typesListExist =  typeRepository.findByProduitsIdProduit(idproduit);
            List<Produit_Color> produit_colorListExist = produit_colorRepository.findByProduitsIdProduit(idproduit);
            if(typesListExist.isEmpty()){
                throw new RuntimeException("list type no exist");
            }
//            ::::::::::::::taille trier
            List<Taille> tailleList = new ArrayList<>();
            for (Types types : typesListExist){
                tailleList.add(types.getTaille());
            }
            infoProduit.put("tailles",tailleList);
//            :::::::::::::::::::::::couleurs trier
            List<Couleurs> couleursList = new ArrayList<>();
            for (Produit_Color produitColor : produit_colorListExist){
                couleursList.add(produitColor.getCouleurs());
            }
            infoProduit.put("produitsCouleur",couleursList);
        }else {
            throw new RuntimeException("produit no exist");
        }
        infoProduit.put("produits",produitsExist);
   return infoProduit;
}
//::::::::::::::::publier un produit:::::::::::::::::;
    public  Produits publier(int idProduit){
        Produits produitsExist = produitRepository.findByIdProduit(idProduit);
        if (produitsExist!=null){
            produitsExist.setPublier(!produitsExist.isPublier());

            Artisans artisanExist = artisanRepository.findByIdArtisans(produitsExist.getArtisans().getIdArtisans());
            if(artisanExist!=null){
                String message = notificationService.messagemailPublierProduit();

                String subject = " Acceptation du produit publié sur ArtEshop";
                String result = sendSimpleMail(artisanExist.getEmail(),message, subject);

            }
            return produitRepository.save(produitsExist);
        }else {
            throw new RuntimeException("produit no exist");
        }

    }

//    ::::::::::::::::::::::message

    public String sendSimpleMail(String email , String messagemail,String subject)
    {
        final String fromEmail = "ousmatotoure73@gmail.com";
        Artisans artisans = artisanRepository.findByEmail(email);

        try {
            String message = notificationService.messagemailPublierProduit();
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(artisans.getEmail());
            mailMessage.setText("Bonjour! "+ artisans.getNom()+" "+message);
            mailMessage.setSubject(subject);

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

//::::::::::::::regeter un produit
    public  String rejeter(int idProduit){
        Produits produitsExist = produitRepository.findByIdProduit(idProduit);
        if (produitsExist!=null){

            Artisans artisanExist = artisanRepository.findByIdArtisans(produitsExist.getArtisans().getIdArtisans());
            if(artisanExist!=null){
                String message = notificationService.messagemailrejeterProduit();
                String subject = " Rejet du produit publié sur ArtEshop";
                notificationRepository.deleteAllByProduitsIdProduit(produitsExist.getIdProduit());
                produitRepository.deleteById(produitsExist.getIdProduit());
                String result = sendSimpleMail(artisanExist.getEmail(),message,subject);
            }

        }
        return "success";
    }
//    :::::::::::::list produit similaire pour mobile
    public  List<Produits> produitsListMobile(int idCategorie, String nomProduit){
        List<Produits> produitsExist = produitRepository.findByCategoriesIdCategorieAndNomContaining(idCategorie,nomProduit);
        if (produitsExist.isEmpty()){
            throw new RuntimeException("not exist");
        }
        return produitsExist;
    }

}

