package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.*;
import com.artEshop.com.ArtEshop.Repository.*;
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

    @Autowired
    private CommandeRepository commandeRepository;

    public  Notification read(int idNotification){
        Notification notificationExist = notificationRepository.findByIdNotification(idNotification);
        if(notificationExist==null){
          throw  new RuntimeException(" not exist ");

        }
        return notificationExist;
    }

    public List<Notification> reaAll(){
        List<Notification> pubilierProduit = notificationRepository.findAllByProduitsPublier(false);
        return pubilierProduit;
    }

//    :::::::::::::list notification par artisan
    public  List<Notification> listNotificationArtisan(int idArtisant){
        Artisans artisanExist = artisanRepository.findByIdArtisans(idArtisant);
List<Notification> notificationList = notificationRepository.findByArtisansIdArtisansAndCommandesIsNotNull(idArtisant);
        if (!notificationList.isEmpty()){
            return notificationList;
        }
        throw new RuntimeException("artisan no exist");
    }

    public Notification messageArtisan(int idCommande){
        System.out.println("------------------------notification-----------------------------");
        Commandes commandesExist = commandeRepository.findByIdCommande(idCommande);
        if(commandesExist != null){
            Artisans artisanExist = artisanRepository.findByIdArtisans(commandesExist.getProduits().getArtisans().getIdArtisans());
            DateTimeFormatter dateNotificationFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
            System.out.println("yyyy/MM/dd hh:mm:ss-> " + dateNotificationFormatter.format(LocalDateTime.now()));
            String dateNotification = dateNotificationFormatter.format(LocalDateTime.now());

            Notification notification = new Notification();
            System.out.println("=========================++++++++++++++++++");

            notification.setDate(dateNotification);
            System.out.println("=========================++++++++++++++++++");

            notification.setArtisans(artisanExist);
            notification.setCommandes(commandesExist);
            notification.setProduits(commandesExist.getProduits());
            System.out.println("===============commande et produit==========++++++++++++++++++");

            System.out.println(artisanExist);
            System.out.println("=========================++++++++++++++++++");
            notification.setDescription(
                    " client Cher "+" "+artisanExist.getNom()+
                            "\n" +
                            "! Nous avons le plaisir de vous informer qu'un utilisateur dépuis  "+notification.getCommandes().getUtilisateur().getPays()+" a récemment commandé l'un de vos produits le "+dateNotification+". Votre création continue de séduire notre clientèle. " +
                            "!\n" +
                            " Votre talent est inestimable pour notre communauté."+
                            "\n" +
                            "Continuez à inspirer avec vos créations " +
                            "\n" +
                            "Cordialement,\n" +
                            "L'équipe d'ArtEshop"

            );
            System.out.println("================++++++++++++++++save notification+++++++++++++++++");
            System.out.println(notification);
           return notificationRepository.save(notification);
//            System.out.println("=========================++++++++++++++++++");

        }else
            throw new RuntimeException("no commande find");

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

    //::::::::::::::message qui doit etre envoyer
    public String messagemailPublierProduit(){
        return
                "Nous sommes ravis de vous informer que votre compte artisan a été activé avec succès sur notre plateforme ! Vous êtes désormais prêt à profiter de tous les avantages offerts par notre communauté." +
                "\n" +
                "N'hésitez pas à explorer les fonctionnalités de notre plateforme, à mettre en valeur vos compétences et à interagir avec d'autres membres pour développer votre activité." +
                "\n" +
                "Nous vous souhaitons beaucoup de succès dans vos projets et restons à votre disposition pour toute question ou assistance supplémentaire." +
                "\n" +
                "Cordialement," +
                "\n" +
                "[L'équipe de ArtEshop]";
    }

    //::::::::::::::message qui doit etre envoyer pour rejet
    public String messagemailrejeterProduit(){
        return
        " Nous tenons à vous informer que le produit que vous avez récemment publié sur notre plateforme a été rejeté pour les raisons suivantes :" +
        "\n" +
        "Raison du rejet : non-conformité aux normes de qualité, manque d'informations," +
        "\n" +
        "Nous comprenons que cela peut être décevant, mais nous sommes là pour vous aider à améliorer votre publication. N'hésitez pas à revoir les critères requis ou à nous contacter pour toute clarification supplémentaire." +
        "\n" +
        "Votre engagement envers la qualité est apprécié, et nous sommes convaincus que vous pourrez rapidement rectifier ces points pour proposer un produit conforme et de grande qualité." +
        "\n" +
        "Si vous avez des questions ou besoin d'assistance pour comprendre les exigences pour la publication sur notre plateforme, notre équipe est à votre disposition pour vous aider." +
        "\n" +
        "Nous vous remercions pour votre compréhension et votre coopération." +
        "\n" +
        "Cordialement," +
        "\n" +
        "[L'équipe de ArtEshop]";
    }
}
