package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Artisans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArtisanRepository extends JpaRepository<Artisans, Integer> {
    Artisans findByIdArtisans(int idArtisan);
    Artisans findByEmail( String email);
    Artisans findByEmailAndNom( String email, String nom);
    Artisans findByEmailAndPassword( String email, String password);
    Artisans findByEmailAndPasswordAndActive(String email, String password,boolean isActive);
    Artisans findByIdArtisansAndActive(int idArtisan, Boolean active);

}
