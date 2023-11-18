package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Entity.User;
import com.artEshop.com.ArtEshop.Repository.ArtisanRepository;
import com.artEshop.com.ArtEshop.Repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class ArtisanService {
    @Autowired
    private ArtisanRepository artisanRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    //methode post
    public Artisans addArtisan(Artisans artisans){
        Artisans artisanExist = artisanRepository.findByEmail(artisans.getEmail());
        if (artisanExist!=null){
            throw new  RuntimeException(" artisan exist ");

        }else {
          return   artisanRepository.save(artisans);
        }
    }
    //   methode get
    public List<Artisans> read(){
        return  artisanRepository.findAll();
    }
    //Methode pour supprimer un utilisateur
    public String delete(int id){
        artisanRepository.deleteById(id);
        return "suppression successful";
    }

    //Methode pour Modifier les informations d'un utilisateur
    public Artisans update(Artisans artisans){
        Artisans artisanExist = artisanRepository.findByIdArtisans(artisans.getIdArtisans());
        if (artisanExist==null) {

            artisanRepository.save(artisans);
            return artisanRepository.findByIdArtisans(artisans.getIdArtisans());
        }else
            throw new RuntimeException("Artisan exist");
    }

    //Methode connexion pour verifier si l'utilisateur existe
    public Artisans connexion(String email, String password){
        Artisans artisans = artisanRepository.findByEmailAndPassword(email, password);
        if(artisans != null){
            return artisans;
        }else throw new EntityExistsException("Artisant doesn't exist");
    }


//::::::::::::::::::::::::::::::::::::::
public Artisans addartisan(Artisans artisans, MultipartFile multipartFile) throws Exception {
    if (artisanRepository.findByEmail(artisans.getEmail()) == null) {

        if (multipartFile != null) {
            String location = "C:\\xampp\\htdocs\\artImage";
            try {
                Path rootlocation = Paths.get(location);
                if (!Files.exists(rootlocation)) {
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                    artisans.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                            artisans.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                            artisans.setPhoto("artImage/" + multipartFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new Exception("some error");
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

        return artisanRepository.save(artisans);
    } else {
        throw new EntityExistsException("Cet email existe déjà");
    }
}
//::::::::::::::::::::::::::::::::::
public String sendSimpleMail(String email , String messagemail)
{
    final String fromEmail = "ousmatotoure73@gmail.com";

//        String verificationCode = generateVerificationCode();

    Artisans artisans = artisanRepository.findByEmail(email);

    // Try block to check for exceptions
    try {

        // Creating a simple mail message
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(artisans.getEmail());
        mailMessage.setText("Bonjour M."+ artisans.getNom()+" "+messagemail());
        mailMessage.setSubject("Confirmation");

        // Sending the mail
        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully...";
    }

    // Catch block to handle the exceptions
    catch (Exception e) {
        return "Error while Sending Mail";
    }
}
//::::::::::::::message qui doit etre envoyer
public String messagemail(){
        return "  Vous ete invite  a vous connectez a votre nom avec votre adresse email et votre mot de passe  ";
}
//::::::::::::::::::::::::::::::; active un artisan
public Artisans active(Artisans artisans ){

    Artisans artisanExist = artisanRepository.findByIdArtisans(artisans.getIdArtisans());
    if (artisanExist!=null) {


        artisans.setActive(true);
        String result = sendSimpleMail(artisanExist.getEmail(),messagemail());

        artisanRepository.save(artisans);
        return artisanRepository.findByIdArtisans(artisans.getIdArtisans());
    }else
        throw new RuntimeException("Artisan n'exist pas");
}
//:::::::::::::::;;;liste a activer
//    public List<Artisans> listeActive(List<Artisans> artisansList){
//
//    }


}
