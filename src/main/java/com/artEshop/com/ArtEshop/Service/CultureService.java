package com.artEshop.com.ArtEshop.Service;

import com.artEshop.com.ArtEshop.Entity.Cultures;
import com.artEshop.com.ArtEshop.Repository.CultureRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CultureService {
    @Autowired
    private CultureRepository cultureRepository;

    public Cultures ajoutCulture(Cultures cultures, MultipartFile multipartFile) throws Exception {
        Cultures cultureExist = cultureRepository.findByNom(cultures.getNom());
        if (cultureExist== null){
            if (multipartFile != null) {
                String location = "C:\\xampp\\htdocs\\artImage\\imageCulture";
                try {
                    Path rootlocation = Paths.get(location);
                    if (!Files.exists(rootlocation)) {
                        Files.createDirectories(rootlocation);
                        Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                        cultures.setPhoto("artImage/imageCulture/" + multipartFile.getOriginalFilename());
                    } else {
                        try {
                            String nom = location + "\\" + multipartFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if (!Files.exists(name)) {
                                Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                                cultures.setPhoto("artImage/imageCulture/" + multipartFile.getOriginalFilename());
                            } else {
                                Files.delete(name);
                                Files.copy(multipartFile.getInputStream(), rootlocation.resolve(multipartFile.getOriginalFilename()));
                                cultures.setPhoto("artImage/imageCulture/" + multipartFile.getOriginalFilename());
                            }
                        } catch (Exception e) {
                            throw new Exception("some error");
                        }
                    }
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }

            return cultureRepository.save(cultures);
        } else {
            throw new EntityExistsException("Cet nom de culture existe déjà");
        }
    }
}
