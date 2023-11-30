package com.artEshop.com.ArtEshop.Repository;

import com.artEshop.com.ArtEshop.Entity.Cultures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CultureRepository extends JpaRepository<Cultures,Integer> {
    Cultures findByNom(String nom);
}
