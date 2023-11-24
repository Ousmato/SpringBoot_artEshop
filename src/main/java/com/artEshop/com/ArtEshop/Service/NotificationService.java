package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Admin;
import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Entity.Notification;
import com.artEshop.com.ArtEshop.Entity.Produits;
import com.artEshop.com.ArtEshop.Repository.AdminRepository;
import com.artEshop.com.ArtEshop.Repository.ArtisanRepository;
import com.artEshop.com.ArtEshop.Repository.NotificationRepository;
import com.artEshop.com.ArtEshop.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ArtisanRepository artisanRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public  Notification read(int idNotification){
        Notification notificationExist = notificationRepository.findByIdNotification(idNotification);
        if(notificationExist==null){
          throw  new RuntimeException(" not exist ");

        }
        return notificationExist;
    }

    public List<Notification> reaAll(boolean isPublic){
        List<Notification> pubilierProduit = notificationRepository.findAllByProduitsPublier(isPublic);
        return pubilierProduit;
    }

    public String messageArtisan(Notification notification){
        Artisans artisanExist = artisanRepository.findByIdArtisans(notification.getArtisans().getIdArtisans());
        DateTimeFormatter dateNotificationFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
        System.out.println("yyyy/MM/dd hh:mm:ss-> " + dateNotificationFormatter.format(LocalDateTime.now()));
        String dateNotification = dateNotificationFormatter.format(LocalDateTime.now());

        notification.setDate(dateNotification);
        notification.setArtisans(artisanExist);
        notification.setDescription(
        " client Cher "+" "+artisanExist.getNom()+"\n" +
                "\n" +
                "Nous espérons que vous allez bien ! Nous avons le plaisir de vous informer qu'un utilisateur dépuis  "+notification.getCommandes().getPanier().getUser().getPays()+" a récemment commandé l'un de vos produits le "+dateNotification+". Votre création continue de séduire notre clientèle " +
                "!\n" +
                "Nous sommes ravis de constater l'intérêt porté à votre travail exceptionnel. Votre art contribue à égayer notre plateforme et à offrir une expérience unique à nos utilisateurs." +
                "\n" +
                "Merci pour votre passion et votre dévouement à créer des articles uniques qui enchantent nos clients. Votre talent est inestimable pour notre communauté."+
                "\n" +
                "Continuez à inspirer avec vos créations " +
                "\n" +
                "Cordialement,\n" +
                "[L'équipe d'ArtEshop]"
            );
        return ("success");
    }

    public Notification messageAdmin(Notification notification){
        Produits produitExist = produitRepository.findByIdProduit(notification.getProduits().getIdProduit());
        if ( produitExist!= null){
            DateTimeFormatter dateNotificationFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
            System.out.println("yyyy/MM/dd hh:mm:ss-> " + dateNotificationFormatter.format(LocalDateTime.now()));
            String dateNotification = dateNotificationFormatter.format(LocalDateTime.now());

            notification.setDate(dateNotification);
            notification.setProduits(produitExist);
            notification.setDescription(
                    "L'artisan "+notification.getArtisans().getNom()+ notification.getArtisans().getPrenom()+" a ajouter un produit dans la  categorie "+notification.getProduits().getCategories().getNom()+" le "+ dateNotification+" a sa Catalogue"+
                            "!\n" +
                            "Veillez verifier les informations du produit pour le publier le plutot possible"
            );

        }
        return notification;
    }
}
