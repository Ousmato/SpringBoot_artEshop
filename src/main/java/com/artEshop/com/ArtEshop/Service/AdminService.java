package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Admin;
import com.artEshop.com.ArtEshop.Entity.Artisans;
import com.artEshop.com.ArtEshop.Repository.AdminRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;


    public String addAdministrateur(Admin admin){
        Admin adminExist = adminRepository.findByEmail(admin.getEmail());
        if (adminExist!=null){
            throw new  RuntimeException(" artisan exist ");

        }else {
            adminRepository.save(admin);
        }
        return " success ";
    }
    //   methode get
    public List<Admin> read(){
        return  adminRepository.findAll();
    }
    //Methode pour supprimer un utilisateur
    public String delete(int id){
        adminRepository.deleteById(id);
        return "suppression successful";
    }

    //Methode pour Modifier les informations d'un utilisateur
    public Admin update(Admin admin){
        Admin adminExist = adminRepository.findByIdAdmin(admin.getIdAdmin());
        if (adminExist==null) {
            adminRepository.save(admin);
            return adminRepository.findByIdAdmin(admin.getIdAdmin());
        }else
            throw new RuntimeException("Admin exist");
    }

    //Methode connexion pour verifier si l'utilisateur existe
    public Admin connexion(String email, String password){
        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        if(admin != null){
            return admin;
        }else throw new EntityExistsException("Admin doesn't exist");
    }

//    :::::::::::::::::::::::::::::ajouter admin avec photo::::::::::::::::::::
public Admin addAdmin(Admin admin, MultipartFile multipartFile) throws Exception {
    if (adminRepository.findByEmail(admin.getEmail()) == null) {

        if (multipartFile != null) {
            String location = "C:\\xampp\\htdocs\\artImage";
            try {
                Path rootlocation = Paths.get(location);
                if (!Files.exists(rootlocation)) {
                    Files.createDirectories(rootlocation);
                    Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                    admin.setPhoto("http://10.0.2.2/artImage/" + multipartFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                            admin.setPhoto("http://10.0.2.2/artImage/" + multipartFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                            admin.setPhoto("http://10.0.2.2/artImage/" + multipartFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new Exception("some error");
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

        return adminRepository.save(admin);
    } else {
        throw new EntityExistsException("Cet email existe déjà");
    }
}

}
